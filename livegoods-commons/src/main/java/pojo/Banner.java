package pojo;


import lombok.*;

import java.util.Date;

/**
 * 轮播图片
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banner {

    private String id;

    private String url;

    private Date createTime;
}
