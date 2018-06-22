package main.com.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 这些请求不拦截(上传图片的直接放行)
		String servletPath = ((HttpServletRequest) request).getServletPath();//获取请求的链接
//		System.out.println("servletPath："+servletPath);
		if("/emInterface/employee/uploadFile".equals(servletPath) || "/management/admin/uploadImage".equals(servletPath) || "/common/uploadFile".equals(servletPath)) {
//			System.out.println("不参与封装，直接放行");
			chain.doFilter(request, response);
		}else {
			RequestWrapper requestWrapper = null;
			if(request instanceof HttpServletRequest) {
				requestWrapper = new RequestWrapper((HttpServletRequest) request);
			}
			if(requestWrapper == null) {
				chain.doFilter(request, response);
			}
			chain.doFilter(requestWrapper, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	

}
