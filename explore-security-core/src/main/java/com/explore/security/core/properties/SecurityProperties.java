package com.explore.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by xiaohb on 2018/1/9.
 */
@ConfigurationProperties(prefix = "explore.security")
public class SecurityProperties {

    BrowserProperties browser= new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

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
}
