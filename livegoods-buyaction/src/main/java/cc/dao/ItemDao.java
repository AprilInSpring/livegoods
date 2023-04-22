package cc.dao;

import pojo.Item;

public interface ItemDao {
    //根据key从redis缓存中查询value数据
    Item get(String key);
}
