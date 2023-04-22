package cc.dao.impl;

import cc.dao.DetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pojo.Item;

@Repository
public class DetailsDaoImpl implements DetailsDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Item findById(String id) {
        return mongoTemplate.findById(id, Item.class);
    }
}
