package com.explore.security.core.validate.code.image;

import com.explore.security.core.properties.SecurityProperties;
import com.explore.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * Created by xiaohb on 2018/1/11.
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        int length = ServletRequestUtils.getIntParameter(request.getRequest(),
                "length",securityProperties.getCode().getImage().getLength());
        int width = ServletRequestUtils.getIntParameter(request.getRequest(),
                "width",securityProperties.getCode().getImage().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),
                "height",securityProperties.getCode().getImage().getHeight());
        int expireIn = ServletRequestUtils.getIntParameter(request.getRequest(),
                "expireIn",securityProperties.getCode().getImage().getExpireIn());

        String verifyCode = VerifyCodeUtil.generateTextCode(length);
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(width,height,verifyCode);

        return new ImageCode(bufferedImage,verifyCode,expireIn);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
