package com.qst.bx_web.service.impl;

import com.qst.bx_web.dao.BookDao;
import com.qst.bx_web.pojo.Book;
import com.qst.bx_web.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getAllBooks(int cid, int pageNo, int pageSize) {
        Map<String, Integer> map = new HashMap<>();
        map.put("cid", cid);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        System.out.println(map.size() + ":::::" + map.get("pageSize"));
        return bookDao.getAllBooks(map);
    }

    @Override
    public Book getBookById(int bid) {
        Book book = bookDao.getBookById(bid);
        return book;
    }

    @Override
    public boolean modifyBook(Book book) {
        int res = bookDao.modifyBook(book);
        return res > 0 ? true : false;
    }
}
