package com.qst.bx_web.dao;

import com.qst.bx_web.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDao {
    List<Category> getAllCategorys();
}
