package com.explore.security.core.validate.code.image;

import com.explore.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by xiaohb on 2018/1/10.
 */
public class ImageCode extends ValidateCode {

    private static final long serialVersionUID = 1121644077232099435L;


    private BufferedImage image;

    public ImageCode(BufferedImage image, String code,int expireIn) {
        super(code,expireIn);
        this.image = image;

    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
