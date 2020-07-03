package com.oracle.handler;


import com.oracle.service.ItemHandlerService;
import com.oracle.vo.Item;
import com.oracle.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("order")
public class OrderHandler {

    static int count=0;

    //0701feign的,不用写一大堆了。和本地调用时一样的了
    @Autowired
    ItemHandlerService itemHandlerService;

    //生成订单
    //@PostMapping("make")
    @GetMapping("make")
    public Order make(String name,String items){
        //获得商品号，
        String[] ids=items.split(",");
        Order order=new Order();
        order.setOrderNo(++count);
        order.setUserName(name);
        List<Item> list=new ArrayList<Item>();

        //远程调用商品微服务，
        for (String id : ids) {
            Integer itemNo=Integer.valueOf(id);

            //0701感觉就像本地的，在之前的基础上又更新了。
            Item item=itemHandlerService.getItem(itemNo);     //调了两次，所以不一定是一个provider提供的
            System.out.println("从商品微服务中获得数据："+item);
            list.add(item);
        }
        order.setItems(list);
        System.out.println("生成的订单是"+order);
        return order;
    }

    //远程调用 添加商品
    @PostMapping("saveItem")
    public Item save(Item item){
        return item;
    }
}
