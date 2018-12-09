package com.oracle.ddbookmarket.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.oracle.ddbookmarket.biz.AdminBiz;
import com.oracle.ddbookmarket.bizImpl.AdminBizImpl;
import com.oracle.ddbookmarket.model.Admin;
import com.oracle.ddbookmarket.util.MyBeanUtils;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数值
        Admin admin=new Admin();
		//到数据库对比
		MyBeanUtils.populate(admin, request.getParameterMap());

	/*	String vcode=request.getParameter("vcode");
		String servervcode= (String) request.getSession().getAttribute("validateCode");
		//不区分大小写比较
		System.out.println(vcode+"---------"+servervcode);
		if (!servervcode.equalsIgnoreCase(vcode)){
			request.setAttribute("msg","验证码错误");
			request.setAttribute("admin",admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
        ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        Set<ConstraintViolation<Admin>> validateResult=validator.validate(admin);
        if(validateResult.size()>0){
            Map<String,String> errors=new HashMap<>();
            for (ConstraintViolation<Admin> ev : validateResult ) {
                System.out.println(ev);
                errors.put(ev.getPropertyPath().toString(),ev.getMessage());
            }
            request.setAttribute("errors",errors);
            request.setAttribute("admin",admin);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }*/
		String vcode=request.getParameter("vcode");
		String servervcode= (String) request.getSession().getAttribute("validateCode");
		//不区分大小写比较
		System.out.println(vcode+"---------"+servervcode);
		Map<String,String> errors=new HashMap<>();
		if (!servervcode.equalsIgnoreCase(vcode)){
			errors.put("vcode","验证码错误");
		}
		ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
		Validator validator=validatorFactory.getValidator();
		Set<ConstraintViolation<Admin>> validateResult=validator.validate(admin);
		if(validateResult.size()>0){
			for (ConstraintViolation<Admin> ev : validateResult ) {
				System.out.println(ev);
				errors.put(ev.getPropertyPath().toString(),ev.getMessage());
			}
		}
		if (errors.size()>0){
			request.setAttribute("errors",errors);
			request.setAttribute("admin",admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}


		AdminBiz userbiz=new AdminBizImpl();
		boolean ret =userbiz.findAdmin(admin);

		if (ret) {//为true转发到main.jsp页面
			//记录登录成功
			request.getSession().setAttribute("logined",true);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else {
			//把用户填的信息回填
			request.setAttribute("admin",admin);
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

}
