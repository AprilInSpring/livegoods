package cc.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.Order;

import java.util.List;

@FeignClient("livegoods-order")
public interface OrderService {

    @GetMapping("/order")
    public List<Order> getOrders(@RequestParam("user") String user);
}
