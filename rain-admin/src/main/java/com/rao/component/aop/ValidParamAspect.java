/*
 * Created by: xwc
 * Date: 2018
 * e-mail: xwencong@163.com
 */

package com.rao.component.aop;

import annotation.BeanValid;
import annotation.SimpleValid;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import util.result.ResultMessage;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

/**
 * 验证切面
 *
 * @author raojing
 * @date 2019/11/12 14:12
 */
@Aspect
@Order(6)
@Component
public class ValidParamAspect implements InitializingBean {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 复合类型验证器
     */
    private Validator validator;

    /**
     * 简单类型验证器
     */
    private ExecutableValidator executableValidator;

    /**
     * 匹配controller下所有类的所有方法
     */
    @Pointcut("execution( * com.rao.controller..*.*(..))")
    public void validService(){}

    /**
     *  环绕通知：
     *  环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *  环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("validService()")
    public Object validService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 获取方法的所有参数
        Object[] args = proceedingJoinPoint.getArgs();
        if (args == null || args.length == 0){
            // 没有参数，不用验证
            return proceedingJoinPoint.proceed();
        }

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method currentMethod = methodSignature.getMethod();
        Parameter[] parameters = currentMethod.getParameters();

        //是否有SimpleValid
        boolean hasSimpleValid = false;

        // 先扫一遍BeanValid
        int argLength = args.length;
        for (int i=0; i< argLength; i++) {
            BeanValid beanValidAnnotation = parameters[i].getDeclaredAnnotation(BeanValid.class);
            if (beanValidAnnotation != null) {
                if (args[i].getClass().isArray()) {
                    Object[] arrayArg = (Object[])args[i];
                    for (Object item : arrayArg) {
                        Set<ConstraintViolation<Object>> validResult = this.validator.validate(item, beanValidAnnotation.value());
                        if (!validResult.isEmpty()) {
                            String msg = validResult.iterator().next().getMessage();
                            return ResultMessage.fail().addMessage(msg);
                        }
                    }
                } else {
                    Set<ConstraintViolation<Object>> validResult = this.validator.validate(args[i], beanValidAnnotation.value());
                    if (!validResult.isEmpty()) {
                        String msg = validResult.iterator().next().getMessage();
                        return ResultMessage.fail().addMessage(msg);
                    }
                }
                continue;
            }

            if (!hasSimpleValid) {
                SimpleValid simpleValidAnnotation = parameters[i].getDeclaredAnnotation(SimpleValid.class);
                if (simpleValidAnnotation != null) {
                    hasSimpleValid = true;
                }
            }
        }

        // SimpleValid
        if (hasSimpleValid) {
            Object target = proceedingJoinPoint.getTarget();
            Set<ConstraintViolation<Object>> validResult = this.executableValidator.validateParameters(target, currentMethod, args);
            if (!validResult.isEmpty()) {
                String msg = validResult.iterator().next().getMessage();
                return ResultMessage.fail().addMessage(msg);
            }
        }

        return proceedingJoinPoint.proceed();
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public ExecutableValidator getExecutableValidator() {
        return executableValidator;
    }

    public void setExecutableValidator(ExecutableValidator executableValidator) {
        this.executableValidator = executableValidator;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = factory.getValidator();
        this.executableValidator = factory.getValidator().forExecutables();
    }
}
