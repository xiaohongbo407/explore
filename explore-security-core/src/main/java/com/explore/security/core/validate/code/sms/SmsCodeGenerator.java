package com.explore.security.core.validate.code.sms;

import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.validate.code.ValidateCode;
import com.explore.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by xiaohb on 2018/1/11.
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        int expireIn = ServletRequestUtils.getIntParameter(request.getRequest(),
                "expireIn",securityProperties.getCode().getSms().getExpireIn());
        int length = ServletRequestUtils.getIntParameter(request.getRequest(),
                "length",securityProperties.getCode().getSms().getLength());
        String code = RandomStringUtils.randomNumeric(length);
        return new ValidateCode(code,expireIn);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
