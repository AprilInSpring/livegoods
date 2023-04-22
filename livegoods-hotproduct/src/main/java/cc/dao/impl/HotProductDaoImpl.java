package cc.dao.impl;

import cc.dao.HotProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pojo.Item;

import java.util.List;

//热销商品数据库接口实现类
@Repository
public class HotProductDaoImpl implements HotProductDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Item> getItems(Query query) {
        //根据查询条件查询相应的item信息
        return mongoTemplate.find(query, Item.class);
    }
}
