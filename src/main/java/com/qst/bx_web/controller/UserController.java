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
    public List<User> userInfo() {
        List<User> userList = userService.getAllUser();
        return userList;
    }

    @RequestMapping("userLogin")
    public ModelAndView userLogin(User user, HttpServletRequest request,HttpServletResponse response, HttpSession session) throws IOException {
        ModelAndView mv = new ModelAndView();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user.setUsername(username);
        user.setPassword(password);
        user = userService.userLogin(user);
        System.out.println(user);
        if (user != null) {
            session.setAttribute("user", user);

            Cookie uuid=new Cookie("uuid", "uuid="+UUID.randomUUID());
            Cookie userId=new Cookie("userId", "userId="+user.getId());
            Cookie st=new Cookie("st", "st="+ new Date().getTime());
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
        String email = request.getParameter("email");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date regTime = df.parse(df.format(new Date()));
        String role = "user";
        int status = 0;
        String regIp = request.getRemoteAddr();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRegTime(regTime);
        user.setRole(role);
        user.setStatus(status);
        user.setRegIp(regIp);

        int userId = userService.addUser(user);
        if(userId>0){
            model.addAttribute("username",user.getUsername());
            mv.setViewName("redirect:login");
        }else{
            model.addAttribute("error","注册失败，请重试");
            mv.setViewName("register");
        }
        return mv;
    }
}
