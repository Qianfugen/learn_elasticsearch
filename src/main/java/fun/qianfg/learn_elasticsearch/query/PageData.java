package fun.qianfg.learn_elasticsearch.query;

import lombok.Data;

import java.util.List;

/**
 * @author qianfg
 */
@Data
public class PageData<T> {
    /**
     * 数据
     */
    private List<T> list;

    /**
     * 页码，起始1
     */
    private Integer pageNumber;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 总数据量
     */
    private Integer totalElements;

    public PageData() {
    }

    public PageData(List<T> datas, Integer pageNumber, Integer pageSize,
                    Integer totalElements) {
        this.list = datas;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        if (totalElements % pageSize != 0) {
            this.totalPages = ((totalElements / pageSize) + 1);
        } else {
            this.totalPages = totalElements / pageSize;
        }
    }
}
