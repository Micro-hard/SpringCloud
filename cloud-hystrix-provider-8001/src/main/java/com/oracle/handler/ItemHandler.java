package com.oracle.handler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oracle.service.ItemService;
import com.oracle.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  //说明它是一个controller，并且里面所有的方法都有一个  ResponseBody,不用每隔方法都写了
@RequestMapping("item")
public class ItemHandler {

    @Autowired
    ItemService itemService;

    @GetMapping("get")
    //如果调用这个方法的时候出现异常，就熔断，不返回错误信息，直接调用备选方法，
    //一旦调用失败，并抛出错误信息后，会调用fallbackMethod指定的方法。
    @HystrixCommand(fallbackMethod = "get_fallBack")
    public Item getItem(@RequestParam(value = "itemNo",defaultValue = "1") Integer itemNo){
        System.out.println("---9001:收到get请求，商品好是"+itemNo);
        Item item=itemService.getItem(itemNo);
        if(item==null){//抛出异常，调用备选方法，
            throw new RuntimeException("查无此书");
        }
        return itemService.getItem(itemNo);
    }

    public Item get_fallBack(@RequestParam(value = "itemNo",defaultValue = "1") Integer itemNo){
        System.out.println("熔断了，现在调用的时备选方法"+itemNo);
        Item item=new Item(1,"查无此书",0,0);
        return item;
    }


    @PostMapping("save")
    //得加上          requestbody注解，否则不好使。！！！都是null
    public Item save(@RequestBody Item item){
        System.out.println("9001：save:"+item);
        itemService.save(item);
        return item;
    }
}
