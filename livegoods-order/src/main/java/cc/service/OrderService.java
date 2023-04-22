package cc.service;

import pojo.Order;

import java.util.List;

//订单服务层接口
public interface OrderService {
    List<Order> findOrders(String user);
}
