package com.qst.bx_web.pojo;

import java.util.List;

public class Order {
    private int orderid;
    private String orderdate;
    private int status;
    private double subtotal;
    private User user;
    private List<OrderItem> orderitems;

    public List<OrderItem> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItem> orderitems) {
        this.orderitems = orderitems;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
