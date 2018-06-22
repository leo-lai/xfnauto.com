package main.com.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WeiXinInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private WeixinApptokenService apptokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
	    HttpServletResponse response, Object handler) throws Exception {

	String url = request.getScheme() + "://"; // 请求协议 http 或 https
	url += request.getHeader("host"); // 请求服务器
	url += request.getRequestURI(); // 工程名
	if (request.getQueryString() != null) // 判断请求参数是否为空
	    url += "?" + request.getQueryString(); // 参数
//	System.out.println("拦截地址weixinapi=======" + url);
	// request.setAttribute("url", url);
	return true;

    }
}
