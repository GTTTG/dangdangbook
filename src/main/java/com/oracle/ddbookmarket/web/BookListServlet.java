package com.oracle.ddbookmarket.web;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BookTypeBizImpl;
import com.oracle.ddbookmarket.model.BookType;
import com.oracle.ddbookmarket.model.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookTypeBiz bookTypeBiz=new BookTypeBizImpl();

        String strcurrentPage=request.getParameter("currentPage");
        if(strcurrentPage==null) {
            strcurrentPage="1";
        }
        int currentPage=Integer.parseInt(strcurrentPage);
        String name=request.getParameter("name");
        String strsid=request.getParameter("sid")==null?"-1":request.getParameter("sid");
        int sid=Integer.parseInt(strsid);
        String strBid=request.getParameter("bid")==null?"-1":request.getParameter("bid");
        int bid=Integer.parseInt(strBid);
        System.out.println();
        int totaRow=bookTypeBiz.totaRow(name,sid);
        int totaPage=totaRow% PageConstant.PAGE_SIZE==0?totaRow/PageConstant.PAGE_SIZE:totaRow/PageConstant.PAGE_SIZE+1;

        List<BookType> ls=bookTypeBiz.findAll(currentPage,name,sid);
        //查出总行数
        request.setAttribute("name", name);
        request.setAttribute("ls", ls);
        request.setAttribute("bid", bid);
        request.setAttribute("sid", sid);
        request.setAttribute("totaPage", totaPage);
        request.setAttribute("currentPage", currentPage);
        request.getRequestDispatcher("BookList.jsp").forward(request, response);
    }
}
