package com.how2java.tmall.service;

import java.util.List;

import com.how2java.tmall.pojo.CategoryTry;

public interface CategoryTryService {

	List<CategoryTry> list();

	/**
	 * 事务测试
	 * @param
	 */
	void addTwo();
}
