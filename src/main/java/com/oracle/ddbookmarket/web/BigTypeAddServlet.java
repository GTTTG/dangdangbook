package com.oracle.ddbookmarket.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.ddbookmarket.biz.BigTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BigTypeBizImpl;

@WebServlet("/bigtypeadd")
public class BigTypeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BigTypeAddServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		BigTypeBiz bigTypeBiz = new BigTypeBizImpl();
		boolean tf = bigTypeBiz.save(name);
		if (tf) {
			response.sendRedirect("bookList");
		} else {
			request.setAttribute("name", name);
			request.getRequestDispatcher("BigTypeAdd.jsp").forward(request, response);
		}
	}

}
