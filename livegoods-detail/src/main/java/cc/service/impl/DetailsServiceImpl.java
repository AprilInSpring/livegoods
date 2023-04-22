package cc.service.impl;

import cc.dao.DetailsDao;
import cc.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pojo.Item;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DetailsDao detailsDao;

    @Value("${livegoods.banner.nginx.prefix}")
    private String nginxPrefix;

    @Override
    @Cacheable(cacheNames = "cc.livegoods.details",key = "'Details('+#id+')'")
    public Item getDetails(String id) {
        Item item = detailsDao.findById(id);
        //修改图片地址
        List<String> imgs = item.getImgs();
        ArrayList<String> list = new ArrayList<>();
        for (String img : imgs) {
            img = nginxPrefix+img;
            list.add(img);
        }
        item.setImgs(list);
        return item;
    }
}
