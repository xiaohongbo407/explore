package com.explore.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by xiaohb on 2018/1/9.
 * @author xiaohb
 * @date 2018/1/9 12:00
 */
@ConfigurationProperties(prefix = "explore.security")
public class SecurityProperties {

    /**
     * 浏览器方式配置
     */
    private BrowserProperties browser= new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();
    /**
     * 第三方登录配置
     */
    private SocialProperties social = new SocialProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }


}
