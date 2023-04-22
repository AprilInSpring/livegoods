package cc.dao;

import org.springframework.data.mongodb.core.query.Query;
import pojo.Banner;

import java.util.List;

//数据库接口
public interface BannerDao {
    //从mongo数据库查询轮播图集合
    List<Banner> selectBanners(Query query);
}
