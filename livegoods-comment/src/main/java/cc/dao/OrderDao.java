package cc.dao;

//订单接口
public interface OrderDao {
    //根据订单id修改订单状态，改为已评论
    void updateState(String orderId,int commentState);
}
