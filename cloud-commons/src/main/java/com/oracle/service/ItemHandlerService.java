package com.oracle.service;

import com.oracle.vo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

//此注解说明与EUREKA-PROVIDER的服务进行映射， 这个里面的映射要和provider的映射对应上！！！
@FeignClient("EUREKA-PROVIDER")
public interface ItemHandlerService {//当前的Item接口相当于EUREKA-PROVIDER服务，

    @GetMapping("item/get")
    public Item getItem(@RequestParam(value = "itemNo",defaultValue = "1") Integer itemNo);

    @PostMapping("item/save")
    //得加上          requestbody注解，否则不好使。！！！都是null
    public Item save(@RequestBody Item item);
}
