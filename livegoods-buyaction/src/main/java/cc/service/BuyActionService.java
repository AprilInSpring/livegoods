package cc.service;

import vo.LivegoodsResult;

//预定服务层接口
public interface BuyActionService {
    /**
     * @param id 房屋id
     * @param user 用户手机号
     * @return
     */
    LivegoodsResult buyAction(String id,String user);
}
