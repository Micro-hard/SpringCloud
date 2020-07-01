package com.oracle.handler;


import com.oracle.vo.Item;
import com.oracle.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    //不要导错包，org.springframework.cloud.client.discovery.DiscoveryClient;
    //0624负载均衡不用这个了
//    @Autowired
//    DiscoveryClient discoveryClient;

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

        //0624加了负载均衡后下面的四行都不用了，即使只有一个服务器也可以，但是别忘了配置@LoadBalance。
        //获取当前应用发布的实例，0620                                    provider的名，!!!!,有几个实例就返回几个。
//        List<ServiceInstance> services=discoveryClient.getInstances("EUREKA-PROVIDER");
//        System.out.println("EUREKA-PROVIDER此服务的size："+services.size());
//        //只有一个，调第一个，以后如果再注册了一个叫EUREKA-PROVIDER的服务，就可以 轮询 了，高可用，
//        String url=services.get(0).getUri().toString();//主机+ip
//        System.out.println("url:"+url);

        //远程调用商品微服务，
        for (String id : ids) {
            Integer itemNo=Integer.valueOf(id);
            //对方的方法是get的哦
            //改了这里！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
            //Item item=restTemplate.getForObject("http://localhost:8001/item/get?itemNo={itemNo}", Item.class,itemNo );
            //调用另一个微服务的controller      ,后面这个是固定的，但是前面的有可能变化，！！！！！！！！（多个eureka）
            //Item item=restTemplate.getForObject(url+"/item/get?itemNo={itemNo}",Item.class,itemNo);

            //0624 ribbon配置负载均衡后，负载均衡的   不用discoveryClient的那个url了，直接写服务名就行了！！！！！！！！！！！！（多个服务了）
            Item item=restTemplate.getForObject("http://EUREKA-PROVIDER/item/get?itemNo={itemNo}",Item.class,itemNo);
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
