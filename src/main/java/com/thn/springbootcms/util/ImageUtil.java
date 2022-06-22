package com.thn.springbootcms.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtil {
    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}
