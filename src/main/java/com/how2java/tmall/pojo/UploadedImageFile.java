package com.how2java.tmall.pojo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: yslu
 * @Date: 2019/7/9 20:26
 * @description：上传图片
 */
public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
