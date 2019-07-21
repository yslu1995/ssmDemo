package com.how2java.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.how2java.tmall.comparator.*;
import com.how2java.tmall.pojo.*;
import com.how2java.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    ReviewService reviewService;


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
     *
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
     *
     * @param session
     * @return
     */
    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
        //将user信息移出session
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid, Model model) {
        Product p = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.type_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);
        //获取产品的所有属性值
        List<PropertyValue> pvs = propertyValueService.list(p.getId());
        //获取评价
        List<Review> reviews = reviewService.list(p.getId());
        //设置产品的销量和评价数量
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("reviews", reviews);
        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "fore/product";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name, @RequestParam("password") String password, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);

        if (null == user) {
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("forecategory")
    public String category(int cid, String sort, Model model) {
        //根据cid获取分类Category对象 c
        Category c = categoryService.get(cid);
        //为c填充list产品 默认更具id排序
        productService.fill(c);
        //为产品填充销量和评价数据
        productService.setSaleAndReviewNumber(c.getProducts());
        //获取排序参数sort 不为null 则按照指定的顺序排序
        if (null != sort) {
            switch (sort) {
                case "review":
                    Collections.sort(c.getProducts(), new ProductReviewComparator());
                    break;
                case "date":
                    Collections.sort(c.getProducts(), new ProductDateComparator());
                    break;

                case "saleCount":
                    Collections.sort(c.getProducts(), new ProductSaleCountComparator());
                    break;

                case "price":
                    Collections.sort(c.getProducts(), new ProductPriceComparator());
                    break;

                case "all":
                    Collections.sort(c.getProducts(), new ProductAllComparator());
                    break;
            }
        }

        model.addAttribute("c", c);
        return "fore/category";
    }

    /**
     * 搜索
     *
     * @param keyword
     * @param model
     * @return
     */
    @RequestMapping("foresearch")
    public String search(String keyword, Model model) {

        PageHelper.offsetPage(0, 20);
        List<Product> ps = productService.search(keyword);
        productService.setSaleAndReviewNumber(ps);
        model.addAttribute("ps", ps);
        return "fore/searchResult";
    }

    /**
     * 商品页面直接点击购买选项
     *
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @RequestMapping("forebuyone")
    public String buyone(int pid, int num, HttpSession session) {
        Product p = productService.get(pid);
        int oiid = 0;

        User user = (User) session.getAttribute("user");
        boolean found = false;
        //1 基于用户对象user，查询没有生成订单的订单项集合（还在购物车中）
        List<OrderItem> ois = orderItemService.listByUser(user.getId());
        for (OrderItem oi : ois) {
            if (oi.getProduct().getId().intValue() == p.getId().intValue()) {
                //如果产品是一样的话，就进行数量追加
                oi.setNumber(oi.getNumber() + num);
                orderItemService.update(oi);
                found = true;
                oiid = oi.getId();
                break;
            }
        }

        //如果不存在对应的OrderItem,那么就新增一个订单项OrderItem（查看商品直接购买的情况，购物车没有记录）
        if (!found) {
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return "redirect:forebuy?oiid=" + oiid;
    }

    /**
     * 结算页面
     * @param model
     * @param oiid
     * @param session
     * @return
     */
    @RequestMapping("forebuy")
    public String buy( Model model,String[] oiid,HttpSession session){
        List<OrderItem> ois = new ArrayList<>();
        float total = 0;

        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.get(id);
            total +=oi.getProduct().getPromotePrice()*oi.getNumber();
            ois.add(oi);
        }

        session.setAttribute("ois", ois);
        model.addAttribute("total", total);
        return "fore/buy";
    }
}