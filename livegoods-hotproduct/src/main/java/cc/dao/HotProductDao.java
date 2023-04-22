package cc.dao;

import org.springframework.data.mongodb.core.query.Query;
import pojo.Item;

import java.util.List;

//热销商品数据库接口
public interface HotProductDao {
    //获取热销商品
    List<Item> getItems(Query query);
}
