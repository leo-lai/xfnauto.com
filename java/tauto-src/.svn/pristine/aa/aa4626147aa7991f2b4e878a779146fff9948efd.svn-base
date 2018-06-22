package main.com.Interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 原理是，将所有的地址中包含JSP的访问拦截，将访问重定位到网站的跟目录
 * @author yu
 */
public class URLFilter implements Filter{

    @Override
    public void destroy() {
        // TODO Auto-generated method stub      
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterch) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest httpreq = (HttpServletRequest) request;
        StringBuffer url = httpreq.getRequestURL();
        if(url.indexOf("jsp") > 0)   //判断地址中是否包含"JSP"
        {
            HttpServletResponse httpres = (HttpServletResponse) response;
            httpres.sendRedirect(httpreq.getRequestURL().toString());  //跳转到网站根目录，也可以根据自己的需要重定位到自己的Action
            return;
        }else{ //不包含JSP，则继续执行
            filterch.doFilter(request, response);   
        }
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        
    }

}