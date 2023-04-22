package cc.service;

import vo.LivegoodsResult;

//推荐商品服务层接口
public interface RecommendService {
    LivegoodsResult getRecommendation(String city);
}
