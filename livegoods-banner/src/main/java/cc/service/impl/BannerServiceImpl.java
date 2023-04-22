package cc.service.impl;

import cc.dao.BannerDao;
import cc.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pojo.Banner;
import vo.LivegoodsResult;

import java.util.List;

//BannerService服务层实现类
@Service
@Slf4j
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public LivegoodsResult getBanners() {
        //创建统一返回结果
        LivegoodsResult result = new LivegoodsResult();

        try{
            //构建分页条件
            Query query = new Query();
            query.with(PageRequest.of(0,4));
            //查询结果
            List<Banner> banners = bannerDao.selectBanners(query);
            if (banners == null) {
                result.setStatus(500);
                result.setMsg("数据库没有轮播图数据");
                log.info("轮播图查询失败，没有数据");
            }else {
                result.setStatus(200);
                for (Banner banner : banners) {
                    banner.setUrl(nginxPrefix + banner.getUrl());
                }
                result.setResults(banners);
                log.info("轮播图查询成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMsg("轮播图查询失败");
            log.info("轮播图查询失败，发生异常");
        }

        return result;
    }
}
