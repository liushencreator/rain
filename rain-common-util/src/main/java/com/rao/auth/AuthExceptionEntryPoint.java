package com.rao.auth;

import com.alibaba.fastjson.JSON;
import com.rao.util.result.ResultMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义token异常信息
 * @author raojing
 * @date 2019/12/6 13:00
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {
        
        ResultMessage resultMessage = null;
        
        Throwable cause = authException.getCause();
        if(cause instanceof InvalidTokenException) {
            resultMessage = ResultMessage.build(401, "token失效，请重新获取凭证");
        }else{
            resultMessage = ResultMessage.build(401, "未经授权，不能访问");
        }
        
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write(JSON.toJSONString(resultMessage));
    }
    
}
