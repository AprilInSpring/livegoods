package cc.service;

import vo.LivegoodsResult;

//登录服务接口
public interface LoginService {
    //发送验证码
    LivegoodsResult sendCode(String phone);
    //向redis中保存验证码
    void saveCode(String phone,String code);
    //从redis中获取验证码
    String getCode(String phone);
    //登录
    LivegoodsResult login(String phone, String code);
    //删除验证码
    void delete(String phone);
}
