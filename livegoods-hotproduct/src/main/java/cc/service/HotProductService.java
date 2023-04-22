package cc.service;

import vo.LivegoodsResult;

//热销商品服务层接口
public interface HotProductService {
    LivegoodsResult getItems(String city);
}
