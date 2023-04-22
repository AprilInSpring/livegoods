package cc.controller;

import cc.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LivegoodsResult;

@RestController
public class BannerController {

    @Autowired
    private BannerService bannerService;

    //查询轮播图数据
    @GetMapping("/banner")
    public LivegoodsResult banner(){
        return bannerService.getBanners();
    }
}
