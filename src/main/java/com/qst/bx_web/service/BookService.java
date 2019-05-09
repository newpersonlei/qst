package com.qst.bx_web.service;

import com.qst.bx_web.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks(int cid,int pageNo,int pageSize);

    Book getBookById(int bid);

}
