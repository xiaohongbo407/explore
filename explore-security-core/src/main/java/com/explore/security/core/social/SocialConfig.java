package com.explore.security.core.social;

import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.social.support.ExploreSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Created by xiaohbW on 2018/6/13.
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter{

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;
    /**
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("e_");
        if(connectionSignUp != null){
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer exploreSocialConfigurer(){
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        ExploreSocialConfigurer exploreSocialConfigurer = new ExploreSocialConfigurer(filterProcessesUrl);
        exploreSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return exploreSocialConfigurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return  new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)){};
    }

}
