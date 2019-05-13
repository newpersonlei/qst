package com.qst.bx_web.service.impl;

import com.qst.bx_web.dao.OrderDao;
import com.qst.bx_web.pojo.Order;
import com.qst.bx_web.pojo.OrderItem;
import com.qst.bx_web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrderList(int pageNo, int pageSize) {
        Map<String, Integer> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        return orderDao.getOrderList(map);
    }

    @Override
    public Order getOrderById(int orderid) {
        Order order = orderDao.getOrderById(orderid);
        List<OrderItem> orderItems = orderDao.getOrderItemsByOrderId(orderid);
        order.setOrderitems(orderItems);
        return order;
    }
}
