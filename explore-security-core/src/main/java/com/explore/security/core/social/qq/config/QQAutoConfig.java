package com.explore.security.core.social.qq.config;

import com.explore.security.core.properties.QQProperties;
import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.social.qq.connet.QQConnectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "explore.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectFactory(qqConfig.getProviderId(),qqConfig.getAppId(),qqConfig.getAppSecret());
    }
}
