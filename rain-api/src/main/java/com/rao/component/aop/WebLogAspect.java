package com.rao.component.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 请求日志 aop
 * @author raojing
 * @date 2019/11/12 14:12
 */
@Order(5)
@Aspect
@Component
public class WebLogAspect {
    private static final Logger logger = LoggerFactory.getLogger("WEBLOG");

    //两个..代表所有子目录，最后括号里的两个..代表所有参数
    @Pointcut("execution( * com.rao.controller..*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        logger.info("请求地址 : " +request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("请求的方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * returning的值和doAfterReturning的参数名一致
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("返回值 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}
