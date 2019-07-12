package com.how2java.tmall.controller;

import com.alibaba.fastjson.JSON;
import com.how2java.tmall.pojo.UploadedImageFile;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Author: yslu
 * @Date: 2019/7/9 20:27
 * @description：上传图片
 */
@Controller
public class UploadController {
    @RequestMapping("/uploadImage")
    public ModelAndView upload(HttpServletRequest request, UploadedImageFile file)
            throws IllegalStateException, IOException {
        //随机生成文件名
        String name = RandomStringUtils.randomAlphanumeric(10);
        String newFileName = name + ".jpg";
        //生成文件路径
        File newFile = new File(request.getServletContext().getRealPath("/image"), newFileName);
        System.out.println("测试数据 ---------->"+JSON.toJSONString(newFile));
        newFile.getParentFile().mkdirs();
        //将上传的图片的内容拷贝给newFile    FileCopyUtils.copy(this.content, dest);
        file.getImage().transferTo(newFile);

        //请求结束后跳转至showUploadedFilejsp文件 利用视图定位  补充前后缀
        ModelAndView mav = new ModelAndView("showUploadedFile");
        //将上传对象传递给显示图片的jsp
        mav.addObject("imageName", newFileName);
        return mav;
    }
}
