package com.blog.main.doze.service.Impl;

import com.blog.main.doze.dao.SysUserMapper;
import com.blog.main.doze.domain.SysUser;
import com.blog.main.doze.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int createUser(SysUser user) {
        return sysUserMapper.insertSelective(user);
    }

    /**
     * 获取用户策略：先从缓存中获取用户，没有则取数据表中
     * 数据，再将数据写入缓存
     */
    @Override
    public SysUser findUserById(Byte id) {
        String key = "user_"+id;

        ValueOperations<String,SysUser> operations = redisTemplate.opsForValue();

        boolean  hasKey = redisTemplate.hasKey(key);
        //缓存存在
        if(hasKey){
            SysUser user = operations.get(key);
            System.out.println("==========从缓存中获得数据=========");
            System.out.println(user.getName());
            System.out.println("==============================");
            return user;
        }else {
            SysUser user =  sysUserMapper.selectByPrimaryKey(id);
            System.out.println("==========从数据表中获得数据=========");
            System.out.println(user.getName());
            System.out.println("==============================");
            //插入缓存
            operations.set(key, user, 5, TimeUnit.MINUTES);
            return user;
        }
    }


    /**
     * 更新用户策略：先更新数据表，成功之后，再更新缓存
     */
    @Override
    public int updateUser(SysUser user) {
        int result = sysUserMapper.updateByPrimaryKeySelective(user);

        //更新成功
        if(result>0){
            //缓存存在？
            String key = "user_"+user.getId();
            boolean hasKey = redisTemplate.hasKey(key);
            if(hasKey){
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key=========>"+key);
            }
        }
        return result;
    }

    /**
     * 删除用户策略：删除数据表中数据，然后删除缓存
     */
    @Override
    public int deleteUser(Long id) {
        int result = sysUserMapper.deleteByPrimaryKey(new Byte(id.toString()));
        //删除成功
        if(result>0){
            String key = "user_"+id;
            boolean hasKey = redisTemplate.hasKey(key);
            if(hasKey){
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key=========>"+key);
            }
        }
        return result;
    }
}
