package com.oracle.ddbookmarket.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.ddbookmarket.biz.SmallTypeBiz;
import com.oracle.ddbookmarket.bizImpl.SmallTypeBizImpl;
import com.oracle.ddbookmarket.model.SmallType;
import com.oracle.ddbookmarket.util.MyBeanUtils;
@WebServlet("/smallTypeAdd")
public class SmallTypeAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SmallTypeAddServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmallType smallType=new SmallType();
		MyBeanUtils.populate(smallType, request.getParameterMap());
		SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();
		boolean tf=smallTypeBiz.save(smallType);
		if (tf) {
			response.sendRedirect("main.jsp");
		} else {
			request.setAttribute("smallType", smallType);
			request.getRequestDispatcher("SmallTypeAdd.jsp").forward(request, response);
		}
	}

}
