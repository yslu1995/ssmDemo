package com.how2java.tmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * register.jsp 是放在WEB-INF目录下的，是无法通过浏览器直接访问的。
 * 专门做服务端跳转
 */
@Controller
@RequestMapping("")
public class PageController {
    /**
     * 访问注册页面
     * @return
     */
    @RequestMapping("registerPage")
    public String registerPage() {
        //视图定位到/WEB-INF/jsp 这个目录下 自动补充前后缀  前缀/WEB-INF/jsp 后缀.jsp
        return "fore/register";
    }

    @RequestMapping("registerSuccessPage")
    public String registerSuccessPage() {
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage() {
        return "fore/login";
    }

    @RequestMapping("forealipay")
    public String alipay() {
        return "fore/alipay";
    }
}