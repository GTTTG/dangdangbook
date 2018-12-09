package com.oracle.ddbookmarket.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.oracle.ddbookmarket.biz.BigTypeBiz;
import com.oracle.ddbookmarket.bizImpl.BigTypeBizImpl;
import com.oracle.ddbookmarket.model.BigType;

@WebServlet("/findAllBigtype")
public class FindAllBigTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FindAllBigTypeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callBack=request.getParameter("callback");
		BigTypeBiz bigTypeBiz=new BigTypeBizImpl();
		List<BigType> ls=bigTypeBiz.findAllBigtype();
		//告诉浏览器是java
		response.setContentType("text/javascript;charset=utf-8");
		JSONArray jsonArray=new JSONArray(ls);
		PrintWriter out=response.getWriter();
		out.println(callBack+"("+jsonArray.toString()+")");
		out.flush();
	}

}
