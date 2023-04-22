package cc.service.impl;

import cc.dao.LoginLogDao;
import cc.service.LoginService;
import cc.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pojo.LoginLog;
import vo.LivegoodsResult;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private LoginLogDao loginLogDao;

    //发送验证码
    @Override
    public LivegoodsResult sendCode(String phone) {
        String redisCode = this.getCode(phone);
        if(redisCode != null){
            return LivegoodsResult.error("验证码还可用，请不要重复申请");
        }
        String code = CodeUtil.code();
        messageService.sendCode(phone,code);
        this.saveCode(phone,code);
        return LivegoodsResult.ok();
    }

    //向redis存入验证码
    @Override
    public void saveCode(String phone, String code) {
        redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
    }

    //从redis中获取验证码
    @Override
    public String getCode(String phone) {
        return redisTemplate.opsForValue().get(phone);
    }

    //登录
    @Override
    public LivegoodsResult login(String phone, String code) {
        String redisCode = this.getCode(phone);
        LoginLog loginLog = new LoginLog();
        if(redisCode == null){
            loginLog.setLoginTime(new Date());
            loginLog.setUsername(phone);
            loginLog.setType("code");
            loginLog.setSuccess(false);
            loginLog.setMessage("验证码已过期");
            loginLogDao.insert(loginLog);
            return LivegoodsResult.error("验证码已过期");
        }
        if(redisCode.equals(code)){
            loginLog.setLoginTime(new Date());
            loginLog.setUsername(phone);
            loginLog.setType("code");
            loginLog.setSuccess(true);
            loginLog.setMessage("登录成功");
            loginLogDao.insert(loginLog);
            this.delete(phone);
            return LivegoodsResult.ok();
        }else {
            loginLog.setLoginTime(new Date());
            loginLog.setUsername(phone);
            loginLog.setType("code");
            loginLog.setSuccess(true);
            loginLog.setMessage("验证码错误");
            loginLogDao.insert(loginLog);
            return LivegoodsResult.error("验证码错误");
        }
    }

    //删除验证码
    @Override
    public void delete(String phone) {
        redisTemplate.delete(phone);
    }
}
