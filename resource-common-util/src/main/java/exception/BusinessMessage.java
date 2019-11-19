package exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 简单业务信息类
 *
 * @author raojing
 * @date 2019/11/15 12:37
 */
@Data
@AllArgsConstructor
public class BusinessMessage {

    /**
     * 获取消息的编号
     * @return code
     */
    private Integer code;

    /**
     * 获取消息的内容
     * @return msg
     */
    private String msg;

}
