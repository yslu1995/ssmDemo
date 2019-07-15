package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.pojo.PropertyValue;

import java.util.List;

/**
 * @Author: yslu
 * @Date: 2019-07-15 22:02
 * @description：
 */
public interface PropertyValueService {
    void init(Product p);

    void update(PropertyValue pv);

    /**
     * 根据属性id和产品id获取PropertyValue对象
     */
    PropertyValue get(int ptid, int pid);

    /**
     * 根据产品id获取所有的属性值
     * @param pid
     * @return
     */
    List<PropertyValue> list(int pid);
}
