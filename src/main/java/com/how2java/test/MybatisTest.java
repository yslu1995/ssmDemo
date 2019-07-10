package com.how2java.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.how2java.mapper.CategoryMapper;
import com.how2java.service.CategoryService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {
    static Logger logger = Logger.getLogger(MybatisTest.class);
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testHutool(){
        String dateStr = "2012-12-12 12:12:12";
        Date date = DateUtil.parse(dateStr);
        logger.info(date);
//        System.out.println(date);
    }


    /**
     * 事务型数据插入
     */
    @Test
    public void testAddTwo() {
//        配置一次就会输出一次日志
//        BasicConfigurator.configure();
//        BasicConfigurator.configure();
//        BasicConfigurator.configure();
//        logger.setLevel(Level.DEBUG);
//        PropertyConfigurator.configure("E:\\workspace\\mb-interview\\ssmDemo\\src\\main\\resources\\log4j.properties");
        System.out.println("测试数据 ---------->" + JSON.toJSONString("fcaawf"));
        logger.info("yhubu");
//        categoryService.addTwo();

    }
//     
//    @Test
//    public void testTotal() {
//        int total = categoryMapper.total();
//        System.out.println(total);
//    }
// 
//    @Test
//    public void testList() {
//        Page p = new Page();
//        p.setStart(2);
//        p.setCount(3);
//        List<Category> cs=categoryMapper.list(p);
//        for (Category c : cs) {
//            System.out.println(c.getName());
//        }
//    }

}
