package com.qst.bx_web.service.impl;

import com.qst.bx_web.dao.CategoryDao;
import com.qst.bx_web.pojo.Category;
import com.qst.bx_web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Cacheable(value = "user", key = "#root.methodName")
    @Override
    public List<Category> getAllCategorys() {
        return categoryDao.getAllCategorys();
    }
}
