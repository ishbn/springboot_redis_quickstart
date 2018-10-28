package com.blog.main.doze.controller;

import com.blog.main.doze.domain.SysUser;
import com.blog.main.doze.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ModelAndView createUser(SysUser user){

        int n = userService.createUser(user);

        return new ModelAndView("");
    }

    @GetMapping("/all")
    public SysUser findUser(String id){
        SysUser n = userService.findUserById(new Byte(id));
        return n;
    }
}
