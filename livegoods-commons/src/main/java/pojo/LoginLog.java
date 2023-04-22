package pojo;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginLog {
    //日志id
    private String id;
    //登录人
    private String username;
    //登录方式
    private String type;
    //登录时间
    private Date loginTime;
    //登录是否成功
    private boolean isSuccess;
    //日志消息
    private String message;
}
