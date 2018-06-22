package main.com.Interceptor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import main.com.frame.base.SpringBeanManager;
import main.com.frame.domain.ResultCode;
import main.com.logistics.dao.vo.LogisticsDriverVo;
import main.com.logistics.service.LogisticsDriverService;
import main.com.system.dao.po.SystemUsers;
import main.com.system.service.SystemUsersService;
import main.com.utils.StringUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/** 
* @author liaozijie 
* @version 创建时间：2018年1月17日 下午3:30:24 
* 类描述： 司机端拦截器
*/
public class DriverInterceptor implements HandlerInterceptor{
	
	@Autowired
	private LogisticsDriverService logisticsDriverService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String sessionId = request.getParameter("sessionId");
		if(sessionId == null || sessionId.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))){
				String str = null;
				while((str = br.readLine()) != null) {
					sb.append(str);
				}
				try {
					JSONObject json = JSONObject.fromObject(sb.toString());
					sessionId = (String) json.get("sessionId");
				}catch(JSONException e) {
					
				}			
			}
		}
		if(StringUtil.isEmpty(sessionId)){
			PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
    		try{
    			JSONObject obj = new JSONObject();
    			obj.put("success", false);
        		obj.put("resultCode", ResultCode.CODE_STATE_4002);
    			obj.put("message", "你的登录信息已失效，请重新登录后再操作。");
    			out.println(obj);
    			out.flush();
    		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			out.close();
    		}
    		return false;
		}
		 HttpSession session = request.getSession();
		 LogisticsDriverVo driverVo  = (LogisticsDriverVo) session.getAttribute(sessionId);
		 if(driverVo == null){
			if (logisticsDriverService == null) {
				logisticsDriverService = SpringBeanManager.getBean(LogisticsDriverService.class);
			}
			driverVo = logisticsDriverService.getByCode(sessionId);
			 if(driverVo == null){//如果查询不到登录信息
				response.setCharacterEncoding("UTF-8"); 
	    		response.setContentType("text/json"); 
	    		PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
	    		try{
	    			JSONObject obj = new JSONObject();
	    			obj.put("success", false);
	        		obj.put("resultCode", ResultCode.CODE_STATE_4002);
	    			obj.put("message", "你的登录信息已失效，请重新登录后再操作。");
	    			out.println(obj);
	    			out.flush();
	    		}catch(Exception e){
	    			e.printStackTrace();
	    		}finally{
	    			out.close();
	    		}
	    		return false;
			 }
			 session.setAttribute(sessionId, driverVo);
		 }	 
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
