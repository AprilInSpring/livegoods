package cc.service;

import vo.LivegoodsResult;

//搜索服务接口
public interface SearchService {
    LivegoodsResult search(String city,String content,int page,int rows);
}
