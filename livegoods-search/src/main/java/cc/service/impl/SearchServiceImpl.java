package cc.service.impl;

import cc.dao.Item4ESDao;
import cc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.LivegoodsResult;

//搜索服务接口实现类
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private Item4ESDao item4ESDao;

    @Override
    public LivegoodsResult search(String city, String content, int page, int rows) {
        return LivegoodsResult.ok(item4ESDao.findPage(city,content,page,rows));
    }
}
