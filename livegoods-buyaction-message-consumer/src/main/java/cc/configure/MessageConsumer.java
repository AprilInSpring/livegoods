package cc.configure;

import cc.service.BuyActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pojo.BuyMessage;

import java.util.function.Consumer;

//消息消费类
@Component
@Slf4j
public class MessageConsumer {
    @Autowired
    private BuyActionService buyActionService;

    @Bean
    public Consumer<BuyMessage> livegoodsMessenger(){
        return new Consumer<BuyMessage>() {
            @Override
            public void accept(BuyMessage buyMessage) {
                String itemId = buyMessage.getItemId();
                String username = buyMessage.getUsername();
                log.info("消费消息");
                buyActionService.buyAction(itemId,username);
            }
        };
    }
}
