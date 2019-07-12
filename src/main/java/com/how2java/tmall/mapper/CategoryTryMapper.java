package com.how2java.tmall.mapper;
 
import java.util.List;

import com.how2java.tmall.pojo.CategoryTry;

public interface CategoryTryMapper {
    public int add(CategoryTry categoryTry);

    public void delete(int id);  

    public CategoryTry get(int id);

    public int update(CategoryTry categoryTry);

    public List<CategoryTry> list();
}