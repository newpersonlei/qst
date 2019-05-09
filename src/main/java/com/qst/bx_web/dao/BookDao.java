package com.qst.bx_web.dao;

import com.qst.bx_web.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookDao {
    List<Book> getAllBooks(Map<String,Integer> map);
}
