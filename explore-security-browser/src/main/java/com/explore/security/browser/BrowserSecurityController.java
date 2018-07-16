package com.explore.security.browser;

import com.explore.security.browser.support.SimpleResponse;
import com.explore.security.core.properties.SecurityConstants;
import com.explore.security.core.social.support.SocialUserInfo;
import com.explore.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaohb on 2018/1/9.
 */
@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 请求缓存
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    /**
     * 当需要身份认证时，跳转至这个方法进行逻辑处理
     * @param request 请求
     * @param response 响应
     * @return
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //把当前请求的放到缓存中
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        //判断是否存在这个请求
        if(savedRequest != null){
            //引发跳转的请求URL
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是："+targetUrl);
            //判断是否已.html结尾
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                //跳转至登录界面
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        //返回响应状态码：401；需要用户认证
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录");
    }

    /**
     * 用户第一次社交登录时，会引导用户进行用户注册绑定，此服务用于在注册或绑定页面获取社交用户信息
     * @param request
     * @return
     */
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        SocialUserInfo userInfo = new SocialUserInfo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }
}
