package cc.dao.impl;

import cc.dao.Item4ESDao;
import cc.pojo.Item4ES;
import org.apache.commons.configuration.interpol.ExprLookup;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//es数据接口实现类
@Repository
public class Item4ESDaoImpl implements Item4ESDao {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //批量添加es数据
    @Override
    public void batchIndex(List<Item4ES> item4ESList) {
        this.createIndex();

        ArrayList<IndexQuery> queries = new ArrayList<>();
        for (Item4ES item4ES : item4ESList) {
            queries.add(new IndexQueryBuilder().withObject(item4ES).build());
        }
        elasticsearchRestTemplate.bulkIndex(queries,Item4ES.class);
    }

    //分页查询es数据,并对关键字添加高亮
    @Override
    public List<Item4ES> findPage(String city, String content, int page, int rows) {
        //高亮设置
        HighlightBuilder.Field field = new HighlightBuilder.Field("title");
        field.preTags("<span style='color:red'>");
        field.postTags("</span>");
        //可匹配条件
        BoolQueryBuilder shouldQuery = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title",content))
                .should(QueryBuilders.matchQuery("houseType",content))
                .should(QueryBuilders.matchQuery("rentType",content));
        //必要条件
        BoolQueryBuilder mustQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("city",city))
                .must(shouldQuery);
        //创建搜索条件
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(mustQuery)
                .withHighlightFields(field)
                .withPageable(PageRequest.of(page, rows))
                .build();

        //搜索
        SearchHits<Item4ES> search = elasticsearchRestTemplate.search(query, Item4ES.class);
        //解析结果
        List<SearchHit<Item4ES>> searchHits = search.getSearchHits();
        ArrayList<Item4ES> list = new ArrayList<>();
        //对结果进行遍历
        for (SearchHit<Item4ES> searchHit : searchHits) {
            //获取高亮内容集合
            Map<String, List<String>> map = searchHit.getHighlightFields();
            //构建es交互对象
            Item4ES es = Item4ES.builder()
                    .id(searchHit.getContent().getId())
                    .img(searchHit.getContent().getImg())
                    .price(searchHit.getContent().getPrice())
                    .city(searchHit.getContent().getCity())
                    .houseType(searchHit.getContent().getHouseType())
                    .rentType(searchHit.getContent().getRentType())
                    .build();
            //完成高亮设置
            if(map.containsKey("title")){
                //设置高亮
                es.setTitle(map.get("title").get(0));
            }else {
                es.setTitle(searchHit.getContent().getTitle());
            }
            list.add(es);
        }
        return list;
    }

    //创建es索引
    private void createIndex(){
        IndexOperations indexOps = elasticsearchRestTemplate.indexOps(Item4ES.class);
        if(indexOps.exists()){
            indexOps.delete();
        }
        indexOps.create();
        indexOps.refresh();
    }
}
