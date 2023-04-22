package cc.dao;

import org.springframework.data.mongodb.core.query.Query;
import pojo.Item;

import java.util.List;

//推荐商品数据库接口
public interface RecommendationDao {
    List<Item> recommend(Query query);
}
