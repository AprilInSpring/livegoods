package cc.service.impl;

import cc.dao.ItemDao;
import cc.service.BuyActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Item;
import pojo.Order;

@Service
public class BuyActionServiceImpl implements BuyActionService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public boolean buyAction(String id, String user) {
        //1.完成数据库更新
        itemDao.updateMongo(id,true);
        //2.生成订单
        Item value = itemDao.getItem(id);
        Order order = new Order();
        order.setCommentState(0);
        order.setHouseType(value.getHouseType4Search());
        order.setImg(value.getImg());
        order.setItemId(value.getId());
        order.setPrice(value.getPrice().toString());
        order.setRentType(value.getRentType());
        order.setTitle(value.getTitle());
        order.setUsername(user);
        itemDao.addOrder(order);
        //3.更新缓存
        value.setIsRented(true);
        itemDao.updateRedis(id,value);
        return true;
    }
}
