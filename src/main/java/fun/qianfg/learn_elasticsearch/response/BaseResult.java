package fun.qianfg.learn_elasticsearch.response;

import lombok.Data;

/**
 * @author qianfg
 */
@Data
public class BaseResult<T> {
    public final static String SUCCESS = "000000";
    public final static String FAILED = "000001";

    private String code;

    private String message;

    private T data;

    public BaseResult() {
        this.code = SUCCESS;
        this.message = "";
    }

    public BaseResult(T data) {
        this.code = SUCCESS;
        this.data = data;
        this.message = "";
    }

    public BaseResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
