package com.oracle.ddbookmarket.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oracle.ddbookmarket.biz.BookTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BookTypeBizImpl;
import com.oracle.ddbookmarket.model.BookType;
import com.oracle.ddbookmarket.util.MyBeanUtils;
import com.oracle.ddbookmarket.util.StringEscapeUtils;

/**
 * Servlet implementation class BookAddServlet
 */
@WebServlet("/bookAdd")
@MultipartConfig
public class BookAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookAddServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookType book=new BookType();
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
		//转义防止xss（跨站脚本攻击）攻击
		book.setDescn(StringEscapeUtils.htmlEncode(book.getDescn()));
		BookTypeBiz bookType=new BookTypeBizImpl();
		boolean tf=bookType.save(book);
		if (tf) {
			response.sendRedirect("bookList");
		} else {
			request.setAttribute("book", book);
			request.getRequestDispatcher("BookAdd.jsp").forward(request, response);
		}
		
	}

}
