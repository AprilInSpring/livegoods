package pojo;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    //商品id
    private String id;
    //商品标题
    private String title;
    //销量
    private Long sales;
    //是否为推荐商品
    private Boolean recommendation;
    //热门排序|权重，数学
    private Byte recoSort;
    //商品价格
    private Long price;
    //城市
    private String city;
    //租赁方式-整租\合租
    private String rentType;
    //房屋类型
    private String houseType;
    // 房屋特性， Map集合。集合存储数据内容为： years: "建造年份", type: "房屋类型，几室几厅", level: "所在楼层", style: "装修标准", orientation: "房屋朝向"
    private Map<String, String> info;
    //商品图片
    private List<String> imgs;
    //预订时间
    private Date buytime;
    //是否已出租
    private Boolean isRented;

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean rented) {
        isRented = rented;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return imgs.get(0);
    }

    public void setImg(String img) {
    }

    public String getLink() {
        return "/details/"+id;
    }

    public void setLink(String link) {
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Boolean recommendation) {
        this.recommendation = recommendation;
    }

    public Byte getRecoSort() {
        return recoSort;
    }

    public void setRecoSort(Byte recoSort) {
        this.recoSort = recoSort;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    public String getHouseType() {
        return houseType;
    }

    public String getHouseType4Search() {
        // "楼层 | 几室几厅 - 面积"
        return info.get("level") + " | " + info.get("type") + " - " + houseType;
    }
    public void setHouseType4Search(String houseType4Search){}

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
