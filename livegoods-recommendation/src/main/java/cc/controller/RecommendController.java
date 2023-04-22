package cc.controller;

import cc.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/recommendation")
    public LivegoodsResult getRecommendation(String city){
        return recommendService.getRecommendation(city);
    }
}
