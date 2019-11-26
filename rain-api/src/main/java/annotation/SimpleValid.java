/*
 * Created by: xwc
 * Date: 2018
 * e-mail: xwencong@163.com
 */

package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 简单类型验证注解
 *
 * @author raojing
 * @date 2019/7/5 14:30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface SimpleValid {

}
