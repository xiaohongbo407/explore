package com.explore.security.core.properties;

/**
 * @author xiaohb
 * @program
 * @description
 * @date 2018-07-12 上午9:57
 **/
public class LogoutProperties {

    private String signOutSuccessUrl;

    private String[] deleteCookies;

    public String getSignOutSuccessUrl() {
        return signOutSuccessUrl;
    }

    public void setSignOutSuccessUrl(String signOutUrl) {
        this.signOutSuccessUrl = signOutUrl;
    }

    public String[] getDeleteCookies() {
        return deleteCookies;
    }

    public void setDeleteCookies(String[] deleteCookies) {
        this.deleteCookies = deleteCookies;
    }

}
