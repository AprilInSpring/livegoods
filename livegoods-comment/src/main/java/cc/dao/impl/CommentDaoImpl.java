package cc.dao.impl;

import cc.dao.CommentDao;
import cc.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pojo.Comment;

import java.util.List;

//评论接口实现类
@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //添加新的评论内容
    @Override
    public void add(Comment comment) {
        //新增入库
        mongoTemplate.save(comment);
    }

    //分页查询评论内容
    @Override
    public List<Comment> findComments(Query query) {
        return mongoTemplate.find(query, Comment.class);
    }
}
