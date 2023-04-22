package pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    //用户名
    private String username;

    //评论内容
    private String comment;

    //评分
    private int star;

    //商品id
    private String itemId;
}
