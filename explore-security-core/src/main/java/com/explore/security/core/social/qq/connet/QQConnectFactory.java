package com.explore.security.core.social.qq.connet;

import com.explore.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * Created by xiaohbW on 2018/6/13.
 */
public class QQConnectFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectFactory(String providerId, String appid,String appSecret) {
        super(providerId, new QQServiceProvider(appid,appSecret), new QQAdpter());
    }

}
