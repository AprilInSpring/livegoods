package cc.dao;

import pojo.Item;

//数据库连接层接口
public interface DetailsDao {
    Item findById(String id);
}
