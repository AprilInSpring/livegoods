package cc.dao.impl;

import cc.dao.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pojo.Banner;

import java.util.List;

//数据库连接层实现类
@Repository
public class BannerDaoImpl implements BannerDao {

    //注入mongo数据库操作类
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Banner> selectBanners(Query query) {
        List<Banner> banners = mongoTemplate.find(query, Banner.class);
        return banners;
    }
}
