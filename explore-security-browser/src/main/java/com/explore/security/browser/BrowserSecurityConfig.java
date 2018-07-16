package com.explore.security.browser;

import com.explore.security.core.authentication.AbstractChannelSecurityConfig;
import com.explore.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.explore.security.core.properties.SecurityConstants;
import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Created by xiaohb on 2018/1/8.
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer exploreSocialSecurityConfigurer;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        /*
        开启下面语句，将自动创建token的存储表
        tokenRepository.setCreateTableOnStartup(true);
        */
        return  tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        //TODO 修改配置
        http.apply(validateCodeSecurityConfig)
                .and().apply(smsCodeAuthenticationSecurityConfig)
                .and().apply(exploreSocialSecurityConfigurer)
                .and().rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and().sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                    .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                    .and().logout()
                        .logoutUrl("/signOut")
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .deleteCookies("JSESSIONID")
                .and().authorizeRequests()
                    .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist").permitAll().anyRequest().authenticated()
                .and().csrf().disable();
        ;
    }
}
