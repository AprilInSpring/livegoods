package cc;

import cc.dao.Item4ESDao;
import cc.pojo.Item4ES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Item;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = SearchApplication.class)
@RunWith(SpringRunner.class)
public class CCTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Item4ESDao item4ESDao;

    @Test
    public void init(){
        List<Item> items = mongoTemplate.findAll(Item.class);
        ArrayList<Item4ES> item4ES = new ArrayList<>();
        for (Item item : items) {
            Item4ES es = Item4ES.builder()
                    .title(item.getTitle())
                    .img(item.getImg())
                    .id(item.getId())
                    .city(item.getCity())
                    .price(String.valueOf(item.getPrice()))
                    .houseType(item.getHouseType())
                    .rentType(item.getRentType())
                    .build();
            item4ES.add(es);
        }
        item4ESDao.batchIndex(item4ES);
    }
}
