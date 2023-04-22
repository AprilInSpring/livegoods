package cc.service.impl;

import cc.dao.HotProductDao;
import cc.service.HotProductService;
import org.apache.commons.lang.math.IEEE754rUtils;
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
public class HotProductServiceImpl implements HotProductService {

    @Autowired
    private HotProductDao hotProductDao;

    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;

    //获取热销商品对象
    @Override
    public LivegoodsResult getItems(String city) {
        //构建查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("city").is(city));
        //构建排序
        Sort sort = Sort.by(Sort.Direction.DESC, "sales");
        //构建分页
        PageRequest pageRequest = PageRequest.of(0, 4, sort);
        query.with(pageRequest);
        //查询结果
        List<Item> items = hotProductDao.getItems(query);
        //不足四条时，补足四条，确保数据库数据大于4条
        if(items != null && items.size() < 4){
            Query addQuery = new Query();
            query.addCriteria(Criteria.where("city").ne(city));
            Sort addSort = Sort.by(Sort.Direction.DESC, "sales");
            PageRequest addPage = PageRequest.of(0, 4, sort);
            query.with(addPage);
            List<Item> adds = hotProductDao.getItems(query);
            items.addAll(adds);
        }
        //更细图片地址
        for (Item item : items) {
            List<String> itemImgs = item.getImgs();
            ArrayList<String> list = new ArrayList<>();
            for (String itemImg : itemImgs) {
                itemImg = nginxPrefix+itemImg;
                list.add(itemImg);
            }
            item.setImgs(list);

        }
        return LivegoodsResult.ok(items);
    }
}
