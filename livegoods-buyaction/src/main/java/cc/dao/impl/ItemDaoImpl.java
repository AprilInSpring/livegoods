package cc.dao.impl;

import cc.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pojo.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //根据key获取相应的房屋数据
    @Override
    public Item get(String key) {
        return (Item) redisTemplate.opsForValue().get(key);
    }
}
