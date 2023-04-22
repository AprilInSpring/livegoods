package cc.dao.impl;

import cc.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import pojo.Order;

//订单接口实现类
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //根据订单id，修改订单评论状态
    @Override
    public void updateState(String orderId, int commentState) {
        //先创建查询条件
        Query query = new Query();
        //匹配条件
        Criteria criteria = Criteria.where("_id").is(orderId);
        query.addCriteria(criteria);
        //更新条件
        Update update = Update.update("commentState", commentState);
        //完成更新
        mongoTemplate.updateFirst(query,update,Order.class);
    }
}
