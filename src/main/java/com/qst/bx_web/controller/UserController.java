package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @RequestMapping("userLogin")
    public ModelAndView userLogin(User user, HttpServletRequest request, HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        user = userService.userLogin(user);
        System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user);
            mv.setViewName("redirect:index");
        } else {
            session.setAttribute("error", "登录失败，请重试！");
            mv.setViewName("login");
        }
        return mv;
    }
}
