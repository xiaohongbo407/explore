package com.explore.security.core.validate.code;

import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.validate.code.image.ImageCodeGenerator;
import com.explore.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.explore.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 *
 * @author  Created by xiaohb on 2018/1/11.
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        SmsCodeSender smsCodeSender =new DefaultSmsCodeSender();
//        imageCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeSender;
    }
}
