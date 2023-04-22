package pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateCode {

    private String phone; // 手机号
    private String validateCode; // 验证码
}
