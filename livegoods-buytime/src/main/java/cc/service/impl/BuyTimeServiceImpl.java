package cc.service.impl;

import cc.dao.ItemDao;
import cc.service.BuyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Item;
import vo.LivegoodsResult;

@Service
public class BuyTimeServiceImpl implements BuyTimeService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public LivegoodsResult getBuyTime(String id) {
        //查询具体的租房信息
        Item item = itemDao.getItemById(id);
        //查询相关的时间信息并返回
        LivegoodsResult result = LivegoodsResult.ok();
        result.setTime(item.getBuytime().getTime());
        return result;
    }
}
