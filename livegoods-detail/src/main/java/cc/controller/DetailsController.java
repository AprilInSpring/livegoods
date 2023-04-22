package cc.controller;

import cc.openfeign.OrderService;
import cc.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Item;
import pojo.Order;

import java.util.List;

@RestController
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @Autowired(required = false)
    private OrderService orderService;

    @GetMapping("/details")
    public Item findDetails(String id){
        return detailsService.getDetails(id);
    }

    @GetMapping("/details/order")
    public List<Order> getOrders(String user){
        return orderService.getOrders(user);
    }

}
