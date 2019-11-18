/*
 * Created by: xwc
 * Date: 2018
 * e-mail: xwencong@163.com
 */

package com.rao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Bean验证注解
 *
 * @author xwencong@163.com
 * @date 2018/7/5 11:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BeanValid {

    Class<?>[] value() default {};

}
