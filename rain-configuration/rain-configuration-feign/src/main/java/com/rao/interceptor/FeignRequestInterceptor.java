package com.rao.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Feign 拦截器 用于设置请求头，传递 Token
 * @author raojing
 * @date 2020/1/9 10:27
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 设置请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name, value);
            }
        }

        // 设置请求体，这里主要是为了传递 access_token
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                if ("access_token".equals(name)) {
                    // 将 Token 加入请求头
                    requestTemplate.header("authorization", "Bearer " + value);
                }else {
                    // 其它参数加入请求体
                    body.append(name).append("=").append(value).append("&");
                }
            }
        }

        // 设置请求体
        if (body.length() > 0) {
            // 去掉最后一位 & 符号
            body.deleteCharAt(body.length() - 1);            
            requestTemplate.body(body.toString());
        }
    }
}
