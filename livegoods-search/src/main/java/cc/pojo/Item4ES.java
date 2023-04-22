package cc.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.lang.model.element.TypeElement;

//es类对象，与es全文检索进行对接
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//es索引必须全是小写，lowercase
@Document(indexName = "item")
public class Item4ES {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String rentType;

    @Field(type = FieldType.Keyword)
    private String price;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String houseType;

    @Field(type = FieldType.Keyword)
    private String img;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Keyword)
    private String city;
}
