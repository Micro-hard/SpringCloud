package com.oracle.vo;

import java.io.Serializable;

public class Item implements Serializable {
    Integer itemNo;
    String name;
    int price;
    int count;

    public Item() {
    }

    public Item(Integer itemNo, String name, int price, int count) {

        this.itemNo = itemNo;
        this.name = name;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemNo=" + itemNo +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
