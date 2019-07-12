package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.CategoryTryMapper;
import com.how2java.tmall.pojo.CategoryTry;
import com.how2java.tmall.service.CategoryTryService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryTryTryServiceImpl implements CategoryTryService {
	@Autowired
	CategoryTryMapper categoryTryMapper;
	
	
	@Override
	public List<CategoryTry> list(){
		return categoryTryMapper.list();
	}

	/**
	 * 事务测试
	 * 为数据库操作制定事务性！
	 * @param
	 */
	@Override
	@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
	public void addTwo() {
		CategoryTry c1 = new CategoryTry();
		c1.setName("短的名字");
		categoryTryMapper.add(c1);

		CategoryTry c2 = new CategoryTry();
		c2.setName("名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,");
		categoryTryMapper.add(c2);
	}


}
