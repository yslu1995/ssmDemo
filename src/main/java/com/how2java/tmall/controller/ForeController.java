package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("forehome")
    public String home(Model model) {
        /**
         * 1. 查询所有分类
         * 2. 为这些分类填充产品集合
         * 3. 为这些分类填充推荐产品集合
         * 4. 服务端跳转到home.jsp
         */
        List<Category> cs = categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs", cs);
        return "fore/home";
    }

    /**
     * 注册页面填完数据提交action
     * 重复密码，是否为空等等已校验
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("foreregister")
    public String register(Model model, User user) {
        //1. 通过参数User获取浏览器提交的账号密码
        String name = user.getName();
        //2. 通过HtmlUtils.htmlEscape(name);把账号里的特殊符号进行转义
        //为什么要用 HtmlUtils.htmlEscape？ 因为有些同学在恶意注册的时候，会使用诸如 <script>alert('papapa')</script> 这样的名称，会导致网页打开就弹出一个对话框。 那么在转义之后，就没有这个问题了。
        name = HtmlUtils.htmlEscape(name);
        //3. 判断用户名是否存在
        user.setName(name);
        boolean exist = userService.isExist(name);

        if (exist) {
            String m = "用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            //注 model.addAttribute("user", null); 这句话的用处是当用户存在，服务端跳转到register.jsp的时候不带上参数user, 否则当注册失败的时候，会在原本是“请登录”的超链位置显示刚才注册的名称。 可以试试把这一条语句注释掉观察这个现象
            model.addAttribute("user", null);
            //调回原来的注册页面
            return "fore/register";
        }
        userService.add(user);
        //跳转注册成功页面，重定向，客户端重新发起请求
        return "redirect:registerSuccessPage";
    }

    /**
     * 接收用户账号密码 登录
     * @param name
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session) {
        //因为注册的时候，ForeController.register()，就进行了转义，所以这里也需要转义。有些同学在恶意注册的时候，会使用诸如 <script>alert('papapa')</script> 这样的名称，会导致网页打开就弹出一个对话框。
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);

        if (null == user) {
            model.addAttribute("msg", "账号密码错误");
            //密码错误返回用户登陆页面，服务器内部跳转
            return "fore/login";
        }
        //将user信息存入session
        session.setAttribute("user", user);
        return "redirect:forehome";
    }

    /**
     * 用户退出
     * @param session
     * @return
     */
    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
        //将user信息移出session
        session.removeAttribute("user");
        return "redirect:forehome";
    }

}