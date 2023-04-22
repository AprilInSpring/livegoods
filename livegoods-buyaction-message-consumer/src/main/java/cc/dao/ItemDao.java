package cc.dao;

import pojo.Item;
import pojo.Order;

//数据接口
public interface ItemDao {
    //修改mongodb中的数据
    void updateMongo(String id,Boolean isRented);
    //修改缓存中的数据
    void updateRedis(String id, Item item);
    //新增订单数据
    void addOrder(Order order);
    //根据id获取缓存对象
    Item getItem(String id);
}
