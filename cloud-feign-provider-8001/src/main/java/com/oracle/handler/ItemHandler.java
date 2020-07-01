package com.oracle.handler;

import com.oracle.service.ItemHandlerService;
import com.oracle.service.ItemService;
import com.oracle.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController  //说明它是一个controller，并且里面所有的方法都有一个  ResponseBody,不用每隔方法都写了
@RequestMapping("item")
public class ItemHandler implements ItemHandlerService {//0701实现了定义的接口，可以使用feign了

    @Autowired
    ItemService itemService;

    @GetMapping("get")
    public Item getItem(@RequestParam(value = "itemNo",defaultValue = "1") Integer itemNo){
        System.out.println("---8002:收到get请求，商品好是"+itemNo);
        return itemService.getItem(itemNo);
    }

    @PostMapping("save")
    //得加上          requestbody注解，否则不好使。！！！都是null
    public Item save(@RequestBody Item item){
        System.out.println("8002：save:"+item);
        itemService.save(item);
        return item;
    }
}
