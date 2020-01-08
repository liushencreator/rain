package com.rao.handler;

import com.rao.exception.ErrorMsgEnum;
import com.rao.util.result.ResultMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 权限异常处理器
 * @author raojing
 * @date 2019/12/12 19:54
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    /**
     * 越权异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResultMessage accessDeniedExceptionHandler(AccessDeniedException e){
        ErrorMsgEnum errorMsgEnum = ErrorMsgEnum.ACCESS_DENIED;
        return ResultMessage.build(errorMsgEnum.getCode(), errorMsgEnum.getMsg());
    }
    
}
