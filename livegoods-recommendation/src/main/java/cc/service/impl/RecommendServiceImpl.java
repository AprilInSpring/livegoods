package cc.service.impl;

import cc.dao.RecommendationDao;
import cc.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pojo.Item;
import vo.LivegoodsResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendationDao recommendationDao;

    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;

    @Override
    public LivegoodsResult getRecommendation(String city) {
        //构建查询条件
        Query query = new Query();
        //添加匹配条目
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("city").is(city),Criteria.where("recommendation").is(true));
        query.addCriteria(criteria);
        //排序
        Sort sort = Sort.by(Sort.Direction.DESC,"recoSort");
        //分页
        PageRequest pageRequest = PageRequest.of(0, 4, sort);
        query.with(pageRequest);
        //查询
        List<Item> recommend = recommendationDao.recommend(query);
        //不足四条时进行补全
        if (recommend != null && recommend.size() < 4) {
            Query add = new Query();
            add.addCriteria(Criteria.where("city").ne(city));
            add.with(PageRequest.of(0,4-recommend.size()));
            List<Item> list = recommendationDao.recommend(add);
            recommend.addAll(list);
            recommend = this.changeUrl(recommend);
            return LivegoodsResult.ok(recommend);
        }else if(recommend == null){
            //当没有条目时，进行全查询
            Query all = new Query();
            all.with(PageRequest.of(0,4));
            List<Item> list = recommendationDao.recommend(all);
            list = this.changeUrl(list);
            return LivegoodsResult.ok(list);
        }
        recommend = this.changeUrl(recommend);
        return LivegoodsResult.ok(recommend);
    }

    //进行图片路径补全
    private List<Item> changeUrl(List<Item> items){
        for (Item item : items) {
            List<String> itemImgs = item.getImgs();
            ArrayList<String> list = new ArrayList<>();
            for (String itemImg : itemImgs) {
                itemImg = nginxPrefix+itemImg;
                list.add(itemImg);
            }
            item.setImgs(list);
        }
        return items;
    }
}
