package com.explore.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: explore
 * @description: 短信登录验证拦截器
 * @author: XiaoHongBo
 * @create: 2018-04-02 20:12
 **/
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String EXPLORE_FORM_MOBILE_KEY = "mobile";

    private String mobileParameter = EXPLORE_FORM_MOBILE_KEY;
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String mobile = obtainMobile(request);
        if(mobile == null){
            mobile ="";
        }
        mobile = mobile.trim();

        SmsCodeAuthenticationToken authenticationToken = new SmsCodeAuthenticationToken(mobile);

        setDetails(request,authenticationToken);

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    /**
     *
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request that an authentication request is being created for
     * @param authenticationToken the authentication request object that should have its details
     * set
     */
    private void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authenticationToken) {
        authenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 获取手机号
     * Enables subclasses to override the composition of the Mobile, such as by
     * including additional values and a separator.
     *
     * @param request so that request attributes can be retrieved
     *
     * @return the username that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     *
     * @param mobileParameter
     */
    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter,"Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public String getMobileParameter() {
        return mobileParameter;
    }
}
