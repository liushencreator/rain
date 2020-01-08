package com.rao.constant.user;

/**
 * 用户相关常量
 * @author raojing
 * @date 2020/1/7 18:01
 */
public interface UserCommonConstant {

    /**
     * "" 的加密串
     */
    String DEFAULT_PWD = "$2a$10$fw8KLJvKN7jrDMNQH/9w1.u9JaET2swRYq67fUBsqdMN5eDr9z4iy";

    /**
     * 密码登录
     */
    Integer PWD_LOGIN = 1;

    /**
     * 其他方式登录
     */
    Integer NON_PWD_LOGIN = 2;
    
}
