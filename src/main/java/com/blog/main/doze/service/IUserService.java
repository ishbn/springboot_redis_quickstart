package com.blog.main.doze.service;

import com.blog.main.doze.domain.SysUser;


public interface IUserService {

    int createUser(SysUser user);

    SysUser findUserById(Byte id);

    int deleteUser(Long id);

    int updateUser(SysUser user);
}
