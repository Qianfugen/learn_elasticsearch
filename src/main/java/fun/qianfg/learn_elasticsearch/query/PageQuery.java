package fun.qianfg.learn_elasticsearch.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author qianfg
 */
@Data
public class PageQuery implements Serializable {
    /**
     * 页面大小
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer pageSize;

    /**
     * 页码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer pageNumber;

    /**
     * 排序要求，ASC（升序），DESC（降序），默认ASC。
     * 例：id,desc;name,asc
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String[]> orderBy;

    public PageRequest of() {
        int size = (pageSize == null || pageSize < 0) ? 15 : pageSize;
        int number = (pageNumber == null || pageNumber < 1) ? 0 : pageNumber - 1;
        return PageRequest.of(number, size);
    }
}
