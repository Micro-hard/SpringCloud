package com.oracle.service;

import com.oracle.vo.Item;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

//生产备选对象的工厂！！！！！！！！！
//0702    服务降级                                           放的是自己定义的接口
@Component
public class ItemFallBackFactory implements FallbackFactory<ItemHandlerService> {

    //接口里又多少方法，这个工厂里就有多少个方法，都是备选方法！！！！！！！！！

    //备选方法
    @Override
    public ItemHandlerService create(Throwable throwable) {
        //匿名内部类，重写ItemHandlerService了接口的两个方法
        return new ItemHandlerService() {
            @Override
            public Item getItem(Integer itemNo) {
                return new Item(0,"服务器停止响应test",0,0);
            }

            @Override
            public Item save(Item item) {
                return null;
            }
        };
    }
}
