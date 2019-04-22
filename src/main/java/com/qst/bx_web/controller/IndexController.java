package com.qst.bx_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("welcome","欢迎访问我的网站！");
        return "/index";
    }
}
