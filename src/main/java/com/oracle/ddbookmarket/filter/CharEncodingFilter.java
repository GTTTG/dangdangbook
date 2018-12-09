package com.oracle.ddbookmarket.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
/**
 * 解决servlet代码重复问题
 */
@WebFilter("/*")
public class CharEncodingFilter implements Filter {

    public CharEncodingFilter() {
    	System.out.println("===============================");
    }
	public void destroy() {
		System.out.println("过滤器被销毁了");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("过滤器被创建了");
	}
}
