package com.how2java.tmall.service;
  
import java.util.List;
 
import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;
 
public interface OrderItemService {
      
    void add(OrderItem c);
 
    void delete(int id);
    void update(OrderItem c);
    OrderItem get(int id);
    List list();
 
    void fill(List<Order> os);
 
    void fill(Order o);

    /**
     * 增加根据产品获取销售量的方法：
     * @param pid
     * @return
     */
    int getSaleCount(int  pid);
 
}