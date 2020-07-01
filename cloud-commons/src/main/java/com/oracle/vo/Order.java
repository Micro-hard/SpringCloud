package com.oracle.vo;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    Integer orderNo;
    String userName;

    List<Item> items;

    public Order() {
    }

    public Order(Integer orderNo, String userName, List<Item> items) {
        this.orderNo = orderNo;
        this.userName = userName;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", userName='" + userName + '\'' +
                ", items=" + items +
                '}';
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
