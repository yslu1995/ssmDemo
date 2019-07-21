package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product p);

    void delete(int id);

    void update(Product p);

    Product get(int id);

    List list(int cid);

    void setFirstProductImage(List<Product> p);

    /**
     * 为分类填充产品集合
     * @param categorys
     */
    void fill(List<Category> categorys);

    /**
     * 为多个分类填充产品集合
     * @param category
     */
    void fill(Category category);

    /**
     * 为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
     * @param categorys
     */
    void fillByRow(List<Category> categorys);

    /**
     * 增加为产品设置销量和评价数量的方法：
     */
    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumber(List<Product> ps);

    List<Product> search(String keyword);
}
