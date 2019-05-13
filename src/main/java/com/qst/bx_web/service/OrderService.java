package com.qst.bx_web.service;

import com.qst.bx_web.pojo.Order;
import com.qst.bx_web.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getOrderList(int pageNo, int pageSize);

    Order getOrderById(int orderid);
}
