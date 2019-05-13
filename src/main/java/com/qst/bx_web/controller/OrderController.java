package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.Order;
import com.qst.bx_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("orderlistInfo")
    public ModelAndView orderlistInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String strPageNo = request.getParameter("pageNo");
        String strPageSize = request.getParameter("pageSize");

        int pageNo = ("".equals(strPageNo) || strPageNo == null) ? 1 : Integer.parseInt(strPageNo);
        int pageSize = ("".equals(strPageSize) || strPageSize == null) ? 10 : Integer.parseInt(strPageSize);

        List<Order> list = orderService.getOrderList(pageNo, pageSize);
        session.setAttribute("orderlist", list);
        mv.setViewName("admin/ordermanager");
        return mv;
    }

    @RequestMapping("orderdetailsInfo")
    public ModelAndView orderdetailsInfo(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String orderidStr = request.getParameter("orderid");
        int orderid = ("".equals(orderidStr) || orderidStr == null) ? 0 : Integer.parseInt(orderidStr);
        Order order = orderService.getOrderById(orderid);

        model.addAttribute("order", order);
        mv.setViewName("admin/orderdetails");
        return mv;
    }

}

