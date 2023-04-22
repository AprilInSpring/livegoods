package cc.dao.impl;

import cc.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pojo.Order;

import java.util.List;

//数据处理层
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //根据用户手机号查询相关订单
    @Override
    public List<Order> findByUser(String user) {
        Query query = new Query();
        Criteria criteria = Criteria.where("username").is(user);
        query.addCriteria(criteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }
}
