package com.oracle.ddbookmarket.web;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.biz.SmallTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BookTypeBizImpl;
import com.oracle.ddbookmarket.bizImpl.SmallTypeBizImpl;
import com.oracle.ddbookmarket.model.BookType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;

@WebServlet("/toBookEdit")
public class ToBookEidtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//获取参数
        String strid=request.getParameter("id");
        int id=Integer.parseInt(strid);
        //调用业务层
        BookTypeBiz bookType=new BookTypeBizImpl();
        BookType book=bookType.findBookByid(id);
        SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();

        int bid=smallTypeBiz.findBidByid(book.getSid());
        request.setAttribute("book",book);
        request.setAttribute("bid",bid);

        request.getRequestDispatcher("/BookEdit.jsp").forward(request,response);

    }
    }

