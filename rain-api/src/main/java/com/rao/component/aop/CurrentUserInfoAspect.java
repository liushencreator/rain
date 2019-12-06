package com.rao.component.aop;

import com.alibaba.fastjson.JSON;
import com.rao.annotation.IgnoreTokenAuth;
import com.rao.pojo.bo.CurrentUserInfo;
import com.rao.util.auth.CurrentUserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 当前登录用户
 * @author raojing
 * @date 2019/12/6 23:00
 */
@Aspect
@Order(7)
@Component
public class CurrentUserInfoAspect {

    /**
     * 匹配controller下所有类的所有方法
     */
    @Pointcut("execution( * com.rao.controller..*.*(..))")
    public void currentUserInfo(){}

    @Around("currentUserInfo()")
    public Object currentUserInfo(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method currentMethod = methodSignature.getMethod();

        // 如果方法上标了该注解，直接放过，不注参用户信息
        IgnoreTokenAuth ignoreTokenAuth = currentMethod.getAnnotation(IgnoreTokenAuth.class);
        if(ignoreTokenAuth != null){
            return joinPoint.proceed();
        }

        // 如果参数为空 直接放行
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return joinPoint.proceed();
        }

        // 获取当前用户信息，如果是不需要登录的接口，也没有写 IgnoreTokenAuth 注解，将会报错
        CurrentUserInfo currentUser = null;
        try{
            currentUser = JSON.parseObject(CurrentUserUtil.getCurrentUserInfoJsonFormat(), CurrentUserInfo.class);
        }catch (Exception e){
            return joinPoint.proceed();
        }

        // 如果用户信息为空 直接放行
        if(currentUser == null){
            return joinPoint.proceed();
        }

        // 遍历参数，参数类型和 CurrentUserInfo 一样，赋值
        Parameter[] parameters = currentMethod.getParameters();
        for (int i = 0; i < args.length; i++) {
            Parameter parameter = parameters[i];
            if (CurrentUserInfo.class.equals(parameter.getType())) {
                args[i] = currentUser;
                break;
            }
        }
        return joinPoint.proceed(args);
    }


}
