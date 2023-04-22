package cc.dao.impl;

import cc.dao.RecommendationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pojo.Item;

import java.util.List;

//推荐商品数据库接口实现类
@Repository
public class RecommendationDaoImpl implements RecommendationDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Item> recommend(Query query) {
        return mongoTemplate.find(query, Item.class);
    }
}
