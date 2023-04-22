package cc.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//发送短信工具类
@Service
public class MessageService {

    @Value("${message.accessKeyId}")
    private String key;
    @Value("${message.accessKeySecret}")
    private String secret;

    final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
    final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）

    public void sendCode(String phone,String code){
        try {
            DefaultProfile.addEndpoint("cn-qingdao", "cn-qingdao", product, domain);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao",key,secret);

        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("陈超国的项目");
        request.setTemplateCode("SMS_267430023");
        request.putQueryParameter("RegionId", "cn-qingdao");
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            System.out.println("发送短信成功，验证码为:"+code);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }
    }
}
