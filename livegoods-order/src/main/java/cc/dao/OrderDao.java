package cc.dao;

import pojo.Order;

import java.util.List;

//订单数接口
public interface OrderDao {
    List<Order> findByUser(String user);
}
