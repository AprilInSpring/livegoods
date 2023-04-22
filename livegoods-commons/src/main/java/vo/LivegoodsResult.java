package vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivegoodsResult {
    //200-成功 500-失败
    private int status;
    //返回结果
    private Object results;
    //提示消息
    private String msg;
    //返回的数据
    private Object data;
    //分布返回结果，是否还有更多的数据
    private boolean hasMore;
    //预订时间
    private long time;

    public static LivegoodsResult ok(){
        LivegoodsResult livegoodsResult = new LivegoodsResult();
        livegoodsResult.setStatus(200);
        return livegoodsResult;
    }

    public static LivegoodsResult ok(Object data){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(200);
        result.setData(data);
        return result;
    }

    public static LivegoodsResult error(){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(500);
        return result;
    }

    public static LivegoodsResult error(String msg){
        LivegoodsResult result = new LivegoodsResult();
        result.setStatus(500);
        result.setMsg(msg);
        return result;
    }
}
