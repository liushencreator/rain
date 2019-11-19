package exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 异常信息 枚举
 * @author raojing
 * @date 2019/11/15 12:37
 */
@AllArgsConstructor
public enum DefaultErrorMsgEnum {

    /**
     * 失败
     */
    FAIL(500, "操作失败"),
    
    ;

    /**
     * 编号 
     */
    @Getter
    private Integer code;

    /**
     * 信息 
     */
    @Getter
    private String msg;

}
