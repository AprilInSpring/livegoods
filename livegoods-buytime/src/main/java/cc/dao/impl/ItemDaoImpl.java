package cc.dao.impl;

import cc.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pojo.Item;

//数据接口实现层
@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item getItemById(String id) {
        return mongoTemplate.findById(id,Item.class);
    }
}
