package com.oracle.handler;

import com.oracle.vo.Item;
import com.oracle.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderHandler {

    static int count=0;

    //可以  远程调用，调用其它服务的controller
    @Autowired
    RestTemplate restTemplate;

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
            //对方的方法是get的哦
            Item item=restTemplate.getForObject("http://localhost:8001/item/get?itemNo={itemNo}", Item.class,itemNo );
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

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("itemNo", item.getItemNo());
        map.put("name", item.getName());
        map.put("price", item.getPrice());
        map.put("count", item.getCount());

        //对方的方法是post的哦
        restTemplate.postForObject("http://localhost:8001/item/save", map, Item.class);
        System.out.println("8002添加成功了："+item);
        return item;
    }
}
