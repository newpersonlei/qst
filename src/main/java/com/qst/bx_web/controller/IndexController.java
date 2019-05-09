package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(Model model, HttpSession session) {
        String username = session.getAttribute("user") != null ? ((User) session.getAttribute("user")).getUsername() : "";
        model.addAttribute("welcome", "欢迎" + username + "访问我的网站！");
        return "/index";
    }

    @RequestMapping("login")
    public String login(Model model) {
        return "/login";
    }

    @RequestMapping("register")
    public String retister(Model model) {
        return "/register";
    }

    @RequestMapping("booklist")
    public String booklist(Model model) {
        return "/booklist";
    }
}
