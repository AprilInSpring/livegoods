package cc.service;

import pojo.Item;

//商品详情服务层接口
public interface DetailsService {
    Item getDetails(String id);
}
