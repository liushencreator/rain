package exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常类
 *
 * @author raojing
 * @date 2019/11/15 12:37
 */
public class BusinessException extends RuntimeException{

    @Getter
    @Setter
    private BusinessMessage businessMessage;

    public static BusinessException operate() {
        DefaultErrorMsgEnum errorMsgEnum = DefaultErrorMsgEnum.FAIL;
        return createCodeAndMsg(errorMsgEnum.getCode(), errorMsgEnum.getMsg());
    }

    public static BusinessException operate(String msg) {
        DefaultErrorMsgEnum errorMsgEnum = DefaultErrorMsgEnum.FAIL;
        return createCodeAndMsg(errorMsgEnum.getCode(), msg);
    }

    public static BusinessException operate(DefaultErrorMsgEnum errorMsgEnum) {
        return createCodeAndMsg(errorMsgEnum.getCode(), errorMsgEnum.getMsg());
    }

    private static BusinessException createCodeAndMsg(Integer code, String msg) {
        return new BusinessException(new BusinessMessage(code, msg));
    }

    /**
     * 私有化构造器
     * @param businessMessage
     */
    private BusinessException(BusinessMessage businessMessage) {
        this.businessMessage = businessMessage;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
