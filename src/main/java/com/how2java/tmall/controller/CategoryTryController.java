package com.how2java.tmall.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.CategoryTry;
import com.how2java.tmall.service.CategoryTryService;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

// 告诉spring mvc这是一个控制器类
@Controller
//@RequestMapping("")
public class CategoryTryController {

    @Autowired
    CategoryTryService categoryTryService;
//
//    @RequestMapping("listCategory")
//    public ModelAndView listCategory(Page page) {
//        ModelAndView mav = new ModelAndView();
//        PageHelper.offsetPage(page.getStart(), 5);
//        List<CategoryTry> cs = categoryTryService.list();
//        int total = (int) new PageInfo<>(cs).getTotal();
//
//        page.caculateLast(total);
//
//        // 放入转发参数
//        mav.addObject("cs", cs);
//        // 放入jsp路径
//        mav.setViewName("listCategoryTry");
//        return mav;
//    }


    @ResponseBody
    @RequestMapping("/submitCategory")
    public String submitCategory(@RequestBody CategoryTry categoryTry) {
        System.out.println("SSM接受到浏览器提交的json，并转换为Category对象:" + categoryTry);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/getOneCategory")
    public String getOneCategory() {
        CategoryTry c = new CategoryTry();
        c.setId(100);
        c.setName("第100个分类");
        JSONObject json = new JSONObject();
        json.put("category", JSONObject.toJSON(c));
        System.out.println(JSONObject.toJSON(c));
        System.out.println(json.toJSONString());
        return json.toJSONString();
    }

    @ResponseBody
    @RequestMapping("/getManyCategory")
    public String getManyCategory() {
        List<CategoryTry> cs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CategoryTry c = new CategoryTry();
            c.setId(i);
            c.setName("分类名称:" + i);
            cs.add(c);
        }

        return JSONObject.toJSON(cs).toString();
    }

}
