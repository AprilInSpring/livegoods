package cc.service;

import vo.LivegoodsResult;

//评论服务层接口
public interface CommentService {
    //根据订单id，新增评论记录
    void comment(String orderId,String comment);

    LivegoodsResult findComments(String itemId,int page,int rows);
}
