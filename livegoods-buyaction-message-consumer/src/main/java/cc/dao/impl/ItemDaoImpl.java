package cc.dao.impl;

import cc.dao.ItemDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pojo.Item;
import pojo.Order;

//数据层接口
@Repository
@Slf4j
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    //完成mongo数据库的更新
    @Override
    public void updateMongo(String id, Boolean isRented) {
        log.info("更改数据库");
        log.info("id"+id);
        log.info("isRented"+isRented);
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        Update update = Update.update("isRented", isRented);
        mongoTemplate.updateFirst(query,update, Item.class);
    }

    //完成redis缓存的更新
    @Override
    public void updateRedis(String id, Item item) {
        String key = "cc.livegoods.details::Details("+id+")";
        redisTemplate.opsForValue().set(key, item);
    }

    //新增订单数据到mongo
    @Override
    public void addOrder(Order order) {
        mongoTemplate.save(order);
    }

    @Override
    public Item getItem(String id) {
        String key = "cc.livegoods.details::Details("+id+")";
        return (Item) redisTemplate.opsForValue().get(key);
    }
}
