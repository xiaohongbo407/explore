package com.explore.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by xiaohb on 2018/1/9.
 */
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private String signUpUrl = SecurityConstants.DEFAULT_SIGNIN_PAGE_URL;

    private String signOutUrl;

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = SecurityConstants.DEFAULT_REMEMBER_ME_SECONDS;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }
}
