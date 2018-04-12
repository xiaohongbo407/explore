package com.explore.web.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaohb on 2018/1/5.
 * 可以获取拦截的class的名称和方法，但是并不能获取拦截方法的参数
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /** Logger available to subclasses */
    protected static Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("TimeInterceptor preHandle BeanName : {}",((HandlerMethod)o).getBean().getClass().getName());
        logger.debug("TimeInterceptor preHandle Method : {}",((HandlerMethod)o).getMethod().getName());
        logger.debug("TimeInterceptor preHandle startTime: {}",System.currentTimeMillis());
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.debug("TimeInterceptor postHandle start");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        logger.debug("TimeInterceptor postHandle 耗时：{}秒",System.currentTimeMillis()-start);

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.debug("TimeInterceptor afterCompletion start");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        logger.debug("TimeInterceptor afterCompletion耗时：{}秒",System.currentTimeMillis()-start);
        logger.debug("TimeInterceptor afterCompletion耗时 Exception is "+e);
    }
}
