package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.CategoryExample;
import com.how2java.tmall.util.Page;

import java.util.List;

public interface CategoryMapper {

    //List<Category> list(Page page);

    //int total();

    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);

    List<Category> selectByExample(CategoryExample example);
}