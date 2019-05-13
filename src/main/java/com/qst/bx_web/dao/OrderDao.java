package com.qst.bx_web.dao;

import com.qst.bx_web.pojo.Order;
import com.qst.bx_web.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDao {
    List<Order> getOrderList(Map<String, Integer> map);

    Order getOrderById(int orderid);

    List<OrderItem> getOrderItemsByOrderId(int orderid);
}
