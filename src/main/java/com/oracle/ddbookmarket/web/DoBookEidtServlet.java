package com.oracle.ddbookmarket.web;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BookTypeBizImpl;
import com.oracle.ddbookmarket.model.BookType;
import com.oracle.ddbookmarket.util.MyBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/doBookEidt")
@MultipartConfig
public class DoBookEidtServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookType book = new BookType();
        MyBeanUtils.populate(book, request.getParameterMap(), "yyyy-MM-dd");
        String newFile = null;
        Part part = request.getPart("photo");
        if (part.getHeader("Content-Disposition").contains("; filename=")) {
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals("")) {
                // 获得后缀
                String ext = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".") + 1);
                // 新名字 不重复
                newFile = UUID.randomUUID() + "." + ext;
                part.write(request.getServletContext().getRealPath("/upload/" + newFile));
            }
        }
        book.setPhoto(newFile);

        BookTypeBiz bookType = new BookTypeBizImpl();
        boolean tf = bookType.update(book);
        if (tf) {
            response.sendRedirect("bookList");
        } else {
            request.setAttribute("bid",request.getParameter("bid"));
            request.setAttribute("book", book);
            request.getRequestDispatcher("BookEdit.jsp").forward(request, response);
        }
    }

}
