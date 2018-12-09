package com.oracle.ddbookmarket.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorFilter",urlPatterns = "/*")
public class AuthorFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        //以login.jsp和login结尾的不拦截
        if(request.getRequestURI().endsWith("/login.jsp")||request.getRequestURI().endsWith("/login")||request.getRequestURI().contains("/bower_components/")||request.getRequestURI().endsWith("/vcode.png")){
            chain.doFilter(req, resp);
            return;
        }
        if( request.getSession().getAttribute("logined")==null||!(Boolean) request.getSession().getAttribute("logined")){
            response.sendRedirect("login.jsp");
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
