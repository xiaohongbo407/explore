package com.explore.security.core.validate.code.image;

import com.explore.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @program: explore
 * @description: 图片验证码处理器
 * @author: XiaoHongBo
 * @create: 2018-03-28 16:45
 **/

@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
    /**
     * 发送图形验证码，将其写到响应中
     * @param request web请求
     * @param validateCode 验证码对象
     * @throws Exception
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws Exception {
        ImageIO.write(validateCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
