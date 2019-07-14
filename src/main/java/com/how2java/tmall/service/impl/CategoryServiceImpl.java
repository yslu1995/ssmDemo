package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.CategoryMapper;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    static Logger log = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryMapper categoryMapper;

    //    @Override
//    public List<Category> list(Page page) {
//        return categoryMapper.list(page);
//    }
//
    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void delete(int id) {
        log.info("输出信息");
        categoryMapper.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryMapper.get(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }


//    @Override
//    public int total() {
//        return categoryMapper.total();
//    }

}