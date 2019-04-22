package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("userInfo")
    public List<User> userInfo() {
        List<User> userList = userService.getAllUser();
        System.out.println("------------------------------------------");
        System.out.println(userList.size());
        return userList;
    }
}
