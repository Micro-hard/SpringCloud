package com.oracle.service;

import com.oracle.vo.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {

    public static Map<Integer,Item> map=new HashMap<Integer, Item>();
    static {
        map.put(1, new Item(1,"刻意练习",25,100));
        map.put(2, new Item(2,"java从入门到精通",20,161));
        map.put(3, new Item(3,"华为P40",200,500));
    }


    public Item getItem(Integer itemNo){
        return map.get(itemNo);
    }

    public void save(Item item){
        map.put(item.getItemNo(), item);
    }


}
