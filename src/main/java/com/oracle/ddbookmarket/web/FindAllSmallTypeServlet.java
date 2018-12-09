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

import com.oracle.ddbookmarket.biz.SmallTypeBiz;
import com.oracle.ddbookmarket.bizImpl.SmallTypeBizImpl;
import com.oracle.ddbookmarket.model.SmallType;

/**
 * Servlet implementation class BookAddServlet
 */
@WebServlet("/findAllSmallType")
public class FindAllSmallTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllSmallTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid=request.getParameter("bid");
		int bd=Integer.parseInt(bid);
		String callBack=request.getParameter("callback");
		SmallTypeBiz smallTypeBiz=new SmallTypeBizImpl();
		List<SmallType> ls=smallTypeBiz.findAllSmalltype(bd);
		//告诉浏览器是java
		response.setContentType("text/javascript;charset=utf-8");
		JSONArray jsonArray=new JSONArray(ls);
		PrintWriter out=response.getWriter();
		out.println(callBack+"("+jsonArray.toString()+")");
		out.flush();
	}


}
