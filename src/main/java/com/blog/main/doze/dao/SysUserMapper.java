package com.blog.main.doze.dao;

import com.blog.main.doze.domain.SysUser;

public interface SysUserMapper {

    int deleteByPrimaryKey(Byte id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}