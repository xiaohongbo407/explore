package com.explore.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by xiaohb on 2018/1/5.
 */
@Aspect
@Component
public class TimeAspect {

    //@Before("")//在方法之前
    //@After("")//在方法之后
    //@AfterThrowing//报错之后
    @Around("execution(* com.explore.web.controller.HelloController.*(..))")//包围
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("TimeAspect handlerControllerMethod start");
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            System.out.println("TimeAspect arg is " + arg);
        }
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        System.out.println("TimeAspect 耗时："+(System.currentTimeMillis()-start));
        System.out.println("TimeAspect end");
        return object;
    }
}
