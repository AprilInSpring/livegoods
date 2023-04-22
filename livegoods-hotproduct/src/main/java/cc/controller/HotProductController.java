package cc.controller;

import cc.service.HotProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class HotProductController {

    @Autowired
    private HotProductService hotProductService;

    //查询热门商品集合
    @GetMapping("/hotProduct")
    public LivegoodsResult getHotProducts(String city){
        return hotProductService.getItems(city);
    }
}
