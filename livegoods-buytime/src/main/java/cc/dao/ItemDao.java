package cc.dao;

import pojo.Item;

//数据接口层
public interface ItemDao {
    Item getItemById(String id);
}
