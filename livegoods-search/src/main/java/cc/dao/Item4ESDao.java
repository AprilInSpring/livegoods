package cc.dao;

import cc.pojo.Item4ES;

import java.util.List;

//ES数据接口
public interface Item4ESDao {
    //批量插入数据，同步数据到es
    void batchIndex(List<Item4ES> item4ESList);
    //分页查询es数据
    List<Item4ES> findPage(String city,String content,int page,int rows);
}
