package cc.dao;

import org.springframework.data.mongodb.core.query.Query;
import pojo.Comment;

import java.util.List;

//评论接口
public interface CommentDao {
    //添加评论信息
    void add(Comment comment);
    //查询评论内容
    List<Comment> findComments(Query query);
}
