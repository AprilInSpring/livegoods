package cc.dao.impl;

import cc.dao.LoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import pojo.LoginLog;

//日志接口实现类
@Repository
public class LoginLogDaoImpl implements LoginLogDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    //向mongodb中插入登录日志记录
    @Override
    public void insert(LoginLog loginLog) {
        mongoTemplate.save(loginLog);
    }
}
