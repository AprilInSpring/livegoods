package cc.service.impl;

import cc.dao.CommentDao;
import cc.dao.OrderDao;
import cc.service.CommentService;
import org.apache.commons.lang.math.IEEE754rUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pojo.Comment;
import pojo.Order;
import vo.LivegoodsResult;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private OrderDao orderDao;

    //根据订单id,新增评论记录
    @Override
    public void comment(String orderId, String comment) {
        //模拟订单数据
        Order order = new Order();
        order.setUsername("cc");
        order.setItemId("63e8d01ac2a99baf595c5bfa");
        //新建评论数据
        Comment newComment = new Comment();
        newComment.setStar(3);
        newComment.setComment(comment);
        newComment.setUsername(order.getUsername());
        newComment.setItemId(order.getItemId());
        //新增评论数据
        commentDao.add(newComment);
        //更新订单评论状态
        orderDao.updateState(orderId,2);
    }

    //根据商品id查询评论内容，page为页码，rows为每页条数
    @Override
    public LivegoodsResult findComments(String itemId, int page, int rows) {
        //构建查询条件
        Query query = new Query();
        //构建匹配条件
        Criteria criteria = Criteria.where("itemId").is(itemId);
        query.addCriteria(criteria);
        //构建分页条件
        query.with(PageRequest.of(page,rows));
        //查询评论
        List<Comment> comments = commentDao.findComments(query);
        //获取评论条数
        Long size = Long.valueOf((comments==null)?0:comments.size());
        //修改手机号
        for (Comment comment : comments) {
            String phone = comment.getUsername().replaceAll("(\\d{3}\\d{4})\\d{4}", "$1****$2");
            comment.setUsername(phone);
        }
        //创建返回结果
        LivegoodsResult result = new LivegoodsResult();
        Long totalPage = ((size%rows==0)?size/rows:size/rows+1);
        if((page+1)<totalPage){
            result.setHasMore(true);
        }else {
            result.setHasMore(false);
        }
        result.setResults(comments);
        return result;
    }
}
