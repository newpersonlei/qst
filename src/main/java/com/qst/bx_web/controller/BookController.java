package com.qst.bx_web.controller;

import com.qst.bx_web.pojo.Book;
import com.qst.bx_web.pojo.Category;
import com.qst.bx_web.pojo.User;
import com.qst.bx_web.service.BookService;
import com.qst.bx_web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("categoryInfo")
    public List<Category> categoryInfo() {
        List<Category> categoryList = categoryService.getAllCategorys();
        return categoryList;
    }

    @RequestMapping("booklistInfo")
    public ModelAndView booklistInfo(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String strCid = request.getParameter("cid");
        String strPageNo = request.getParameter("pageNo");
        String strPageSize = request.getParameter("pageSize");

        int cid = ("".equals(strCid) || strCid == null) ? 0 : Integer.parseInt(strCid);
        int pageNo = ("".equals(strPageNo) || strPageNo == null) ? 1 : Integer.parseInt(strPageNo);
        int pageSize = ("".equals(strPageSize) || strPageSize == null) ? 10 : Integer.parseInt(strPageSize);

        if (session.getAttribute("categorylist") == null) {
            List<Category> categoryList = categoryService.getAllCategorys();
            session.setAttribute("categorylist", categoryList);
        }

        List<Book> list = bookService.getAllBooks(cid, pageNo, pageSize);
        session.setAttribute("booklist", list);
        mv.setViewName("booklist");
        return mv;
    }

    @RequestMapping("admin_booklistInfo")
    public ModelAndView admin_booklistInfo(User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String strCid = request.getParameter("cid");
        String strPageNo = request.getParameter("pageNo");
        String strPageSize = request.getParameter("pageSize");

        int cid = ("".equals(strCid) || strCid == null) ? 0 : Integer.parseInt(strCid);
        int pageNo = ("".equals(strPageNo) || strPageNo == null) ? 1 : Integer.parseInt(strPageNo);
        int pageSize = ("".equals(strPageSize) || strPageSize == null) ? 10 : Integer.parseInt(strPageSize);

        if (session.getAttribute("categorylist") == null) {
            List<Category> categoryList = categoryService.getAllCategorys();
            session.setAttribute("categorylist", categoryList);
        }

        List<Book> list = bookService.getAllBooks(cid, pageNo, pageSize);
        session.setAttribute("booklist", list);
        mv.setViewName("admin/bookmanager");
        return mv;
    }

    //bookdetailsInfo
    @RequestMapping("bookdetailsInfo")
    public ModelAndView bookdetailsInfo(Model model, User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String strBid = request.getParameter("bid");
        int bid = ("".equals(strBid) || strBid == null) ? 0 : Integer.parseInt(strBid);
        Book book = bookService.getBookById(bid);

        model.addAttribute("book", book);
        mv.setViewName("bookdetails");
        return mv;
    }

    @RequestMapping("admin_bookdetailsInfo")
    public ModelAndView admin_bookdetailsInfo(Model model, User user, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String strBid = request.getParameter("bid");
        int bid = ("".equals(strBid) || strBid == null) ? 0 : Integer.parseInt(strBid);
        Book book = bookService.getBookById(bid);

        model.addAttribute("book", book);
        mv.setViewName("admin/bookdetails");
        return mv;
    }

    @RequestMapping("modifyBook")
    public ModelAndView modifyBook(Model model, Book book, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();

        book.setAuthor(request.getParameter("author"));
        book.setBookid(Integer.parseInt(request.getParameter("bookid")));
        book.setCatalog(request.getParameter("catalog"));
        Category category = new Category();
        category.setCid(Integer.parseInt(request.getParameter("cid")));
        book.setCategory(category);
        book.setContent(request.getParameter("content"));
        book.setDescription(request.getParameter("description"));
        book.setPic1(request.getParameter("pic1"));
        book.setPic2(request.getParameter("pic2"));
        book.setPic3(request.getParameter("pic3"));
        book.setPress(request.getParameter("press"));
        book.setPublishdate(request.getParameter("publishdate"));
        book.setTitle(request.getParameter("title"));
        book.setUnitprice(Double.parseDouble(request.getParameter("unitprice")));

        boolean res = bookService.modifyBook(book);
        if (res) {
            model.addAttribute("book", book);
        } else {
            // response.getWriter().write("<script>alert('修改失败，请重试');</script>");
            model.addAttribute("book", bookService.getBookById(book.getBookid()));
        }
        mv.setViewName("admin/bookdetails");
        return mv;
    }
}

