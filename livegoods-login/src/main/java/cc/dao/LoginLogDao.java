package cc.dao;

import pojo.LoginLog;

//登录日志接口
public interface LoginLogDao {
    //向mongodb插入登录日志
    void insert(LoginLog loginLog);
}
