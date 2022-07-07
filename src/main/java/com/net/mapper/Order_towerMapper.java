package com.net.mapper;

import com.net.entity.Order_tower;

import java.util.List;
import java.util.Map;

public interface Order_towerMapper {

    List<Order_tower> selAllOrder_tower(Map<String, Object> map);

    int addOrder_tower(Order_tower notice);

    int updOrder_tower(Order_tower notice);

    int delOrder_tower(Integer id);

    Order_tower selOrder_towerId(Integer id);
    List<Order_tower> selOrder_towerByName(String name);
    List<Order_tower> selOrder_towerByNumber(String number);
    List<Order_tower> findOrder_tower();
    Order_tower selOrder_towerUserId(Integer userId);
}
