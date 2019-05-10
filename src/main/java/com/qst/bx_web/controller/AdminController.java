package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.AdminUser;
import com.qst.bx_web.pojo.Category;
import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.CategoryService;
import com.qst.bx_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@EnableAutoConfiguration
public class AdminController {
    @Autowired
    public UserService userService;

    @RequestMapping("admin_index")
    public String admin_index(Model model, HttpSession session) {
        if(session.getAttribute("admin")==null){
            return "/admin/admin_login";
        }
        return "/admin/admin_index";
    }

    @RequestMapping("admin_logout")
    public String admin_logout(Model model, HttpSession session) {
        if(session.getAttribute("admin")!=null){
            session.removeAttribute("admin");
        }
        return "/admin/admin_login";
    }

    @RequestMapping("admin_login")
    public String admin_login(Model model) {
        return "/admin/admin_login";
    }

    @ResponseBody
    @RequestMapping("adminLogin")
    public ModelAndView adminLogin(AdminUser user, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        user = userService.adminLogin(user);

        if (user != null&&user.getRole().equals("root")) {
            session.setAttribute("admin", user);
            mv.setViewName("redirect:admin_index");
        } else {
            session.setAttribute("error", "登录失败，请重试！");
            mv.setViewName("admin_login");
        }
        return mv;
    }
}
