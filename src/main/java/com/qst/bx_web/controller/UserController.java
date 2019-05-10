package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@EnableAutoConfiguration
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping("userInfo")
    public ModelAndView userInfo(Model model, User user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        user = userService.getUserByName(username);
        model.addAttribute("user", user);
        mv.setViewName("admin/userdetails");
        return mv;
    }

    @RequestMapping("modifyUser")
    public ModelAndView modifyUser(Model model, User user, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();
        int userid = Integer.parseInt(request.getParameter("userid"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String sex = request.getParameter("sex");
        user.setUserid(userid);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setCountry(country);
        user.setCity(city);
        user.setSex(sex);

        boolean res = userService.modifyUser(user);
        if (res) {
            model.addAttribute("user", user);
        } else {
            // response.getWriter().write("<script>alert('修改失败，请重试');</script>");
            model.addAttribute("user", userService.getUserByName(user.getUsername()));
        }
        mv.setViewName("admin/userdetails");
        return mv;
    }

    @RequestMapping("userlistInfo")
    public ModelAndView userlistInfo(Model model, User user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String strPageNo = request.getParameter("pageNo");
        String strPageSize = request.getParameter("pageSize");

        int pageNo = ("".equals(strPageNo) || strPageNo == null) ? 1 : Integer.parseInt(strPageNo);
        int pageSize = ("".equals(strPageSize) || strPageSize == null) ? 10 : Integer.parseInt(strPageSize);

        List<User> userlist = userService.getAllUser(pageNo, pageSize);
        model.addAttribute("userlist", userlist);
        mv.setViewName("admin/usermanager");
        return mv;
    }


    @RequestMapping("userLogin")
    public ModelAndView userLogin(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        user = userService.userLogin(user);
        System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user);

            Cookie uuid = new Cookie("uuid", "uuid=" + UUID.randomUUID());
            Cookie userId = new Cookie("userId", "userId=" + user.getUserid());
            Cookie st = new Cookie("st", "st=" + new Date().getTime());
            response.addCookie(uuid);
            response.addCookie(userId);
            response.addCookie(st);

            mv.setViewName("redirect:index");
        } else {
            session.setAttribute("error", "登录失败，请重试！");
            mv.setViewName("login");
        }
        return mv;
    }

    @RequestMapping("addUser")
    public ModelAndView addUser(User user, HttpServletRequest request, HttpSession session, Model model) throws IOException, ParseException {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String sex = request.getParameter("sex");

        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        user.setCity(city);
        user.setCountry(country);
        user.setSex(sex);

        int userId = userService.addUser(user);
        if (userId > 0) {
            model.addAttribute("username", user.getUsername());
            mv.setViewName("redirect:login");
        } else {
            model.addAttribute("error", "注册失败，请重试");
            mv.setViewName("register");
        }
        return mv;
    }
}
