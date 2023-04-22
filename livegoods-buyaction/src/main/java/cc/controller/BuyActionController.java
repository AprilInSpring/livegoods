package cc.controller;

import cc.service.BuyActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class BuyActionController {
    @Autowired
    private BuyActionService buyActionService;

    @GetMapping("/buyaction")
    public LivegoodsResult buyAction(String id,String user){
        return buyActionService.buyAction(id,user);
    }
}
