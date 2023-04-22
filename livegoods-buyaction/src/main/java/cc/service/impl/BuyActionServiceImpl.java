package cc.service.impl;

import cc.dao.ItemDao;
import cc.service.BuyActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import pojo.BuyMessage;
import pojo.Item;
import vo.LivegoodsResult;

//预定服务实现类
@Service
@Slf4j
public class BuyActionServiceImpl implements BuyActionService {

    @Autowired
    private ItemDao itemDao;
    //springCloud消息流操作对象
    @Autowired
    private StreamBridge streamBridge;

    @Override
    public LivegoodsResult buyAction(String id, String user) {
        //根据商品id从缓存中获取房屋数据
        String key = "cc.livegoods.details::Details("+id+")";
        log.info("从缓存中根据key"+key+"获取缓存数据");
        Item item = itemDao.get(key);
        //判断当前房屋是否已被预定
        if(item.getIsRented()){
            LivegoodsResult error = LivegoodsResult.error("当前房屋已被预定，预定失败");
            return error;
        }
        //生成预定消息，完成消息的发送
        BuyMessage message = BuyMessage.builder()
                .itemId(id)
                .username(user)
                .build();
        //利用cloud的消息操作对象完成数据的发送,指定生产者发送消息
        boolean isRented = streamBridge.send("livegoodsMessenger-out-0", message);
        log.info("sendMessage");
        if (isRented){
            //预定成功
            LivegoodsResult ok = LivegoodsResult.ok();
            ok.setMsg("预定成功");
            return ok;
        }else {
            return LivegoodsResult.error("预定失败");
        }
    }
}
