package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.Category;
import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("index")
    public String index(Model model, HttpSession session) {
        String username = session.getAttribute("user") != null ? ((User) session.getAttribute("user")).getUsername() : "";
        model.addAttribute("welcome", "欢迎" + username + "访问我的网站！");
        if (session.getAttribute("categorylist") == null) {
            List<Category> categoryList = categoryService.getAllCategorys();
            session.setAttribute("categorylist", categoryList);
        }
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

    @RequestMapping("bookdetails")
    public String bookdetails(Model model) {
        return "/bookdetails";
    }

    @RequestMapping("basket")
    public String basket(Model model) {
        return "/basket";
    }

    @RequestMapping("order")
    public String order(Model model) {
        return "/order";
    }
}
