package cc.controller;

import cc.service.BuyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class BuyTimeController {

    @Autowired
    private BuyTimeService buyTimeService;

    @GetMapping("/buytime")
    public LivegoodsResult buyTime(String id){
        return buyTimeService.getBuyTime(id);
    }
}
