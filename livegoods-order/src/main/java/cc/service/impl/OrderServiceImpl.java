package cc.service.impl;

import cc.dao.OrderDao;
import cc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Order;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findOrders(String user) {
        return orderDao.findByUser(user);
    }
}
