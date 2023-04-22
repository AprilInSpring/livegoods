package cc.service;

import org.springframework.data.mongodb.core.query.Query;
import pojo.Banner;
import vo.LivegoodsResult;

import java.util.List;

//Banner服务层接口
public interface BannerService {
    LivegoodsResult getBanners();
}
