/*
 * Created by: xwc
 * Date: 2018
 * e-mail: xwencong@163.com
 */

package com.rao.config.exception;

import exception.BusinessException;
import exception.BusinessMessage;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import util.result.ResultMessage;

/**
 * 全局异常处理器
 *
 * @author raojing
 * @date 2019/11/15 12:37
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * 业务异常处理器
     * @param e Exception
     * @return ApiResult
     */
    @ExceptionHandler(BusinessException.class)
    public ResultMessage businessExceptionHandler(BusinessException e) {
        BusinessMessage businessMessage = e.getBusinessMessage();
        return ResultMessage.build(businessMessage.getCode(), businessMessage.getMsg());
    }

    /**
     * 其他异常处理器
     * @param e Exception
     * @return ApiResult
     */
    @ExceptionHandler(Exception.class)
    public ResultMessage defaultExceptionHandler(Exception e) {
        return ResultMessage.fail().addMessage(e.getMessage());
    }
    
    /**
     * 处理请求方式错误引起的异常
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultMessage errorRequestMethod(Exception e) {
        return ResultMessage.fail().addMessage(e.getMessage());
    }

}
