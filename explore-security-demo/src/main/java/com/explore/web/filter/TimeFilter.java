package com.explore.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by xiaohb on 2018/1/5.
 * Filter只能获取ServletRquest和RervletResponse。并不能获取拦截的方法名称
 */
@Component
public class TimeFilter implements Filter {

    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        logger.debug("time filter "+(System.currentTimeMillis()-start));
        logger.debug("time filter finish");

    }

    @Override
    public void destroy() {
        logger.debug("time filter destroy");
    }
}
