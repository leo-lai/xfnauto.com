package main.com.Interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.customer.dao.po.UsersTmpl;
import main.com.customer.service.UsersTmplService;
import main.com.frame.base.SpringBeanManager;
import main.com.frame.domain.ResultCode;
import main.com.utils.StringUtil;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterfaceInterceptor implements HandlerInterceptor{

	  @Autowired
	  private UsersTmplService usersTmplService; //这里使用@Autowired无法注入成功 
	  
	private static final Logger logger = LoggerFactory.getLogger(InterfaceInterceptor.class);
	
	// 这些请求不拦截
    private static final Set<String> UNFILTER_URI = new HashSet<>();
    // 这些开头的请求不拦截
    private static final Set<String> UNFILTER_URI_STARTWITH = new HashSet<>();
    
    static {
//    	 //不拦截：完全匹配的请求
    	UNFILTER_URI.add("/interface/shopUsers/register");//注册
    	UNFILTER_URI.add("/interface/shopUsers/loginCode");//登录
    	UNFILTER_URI.add("/interface/shopUsers/loginPassword");//密码登录
    	UNFILTER_URI.add("/interface/shopUsers/forgetPassword");//忘记密码
    	UNFILTER_URI.add("/interface/shopUsers/phoneVerifyCode");//获取验证码
    	UNFILTER_URI.add("/interface/shopUsers/region");//获取省份
    	UNFILTER_URI.add("/interface/shopGoods/goodsSearch");//搜索商品
    	UNFILTER_URI.add("/interface/carouselFigure/getImages");//获取幻灯片
    	UNFILTER_URI.add("/interface/shopGoods/goodsInfo");//商品详情
    	UNFILTER_URI.add("/interface/shopOrderPay/getConfig");//获取微信授权
    	UNFILTER_URI.add("/interface/shopOrderPay/back/notify");//获取
    	UNFILTER_URI.add("/interface/shopUsers/imageBase64");//获取
    	UNFILTER_URI.add("/interface/shopUsers/silenceLogin");//静默登录
    	UNFILTER_URI.add("/interface/shopUsers/uploadImage");//图片上传
    	UNFILTER_URI.add("/interface/shop/downloadWXQR");//图片上传
    	
    	
    	UNFILTER_URI.add("/interface/agentInfo/loginPassword");//获取
    	UNFILTER_URI.add("/interface/agentInfo/forgetPassword");//获取
    	UNFILTER_URI.add("/interface/shopUsers/binding");//扫码
    	UNFILTER_URI.add("/interface/shopJudge/getJudgeList");//评论列表
    	UNFILTER_URI.add("/interface/agentInfo/loginPasswordNew");//合伙人新登录
    	UNFILTER_URI.add("/interface/agentInfo/forgetPasswordNew");//合伙人新修改密码
    	
    	
    	UNFILTER_URI.add("/interface/receivables/webhooks");//Ping++收款回调通知
    	
    	UNFILTER_URI.add("/interface/weixinapp/login");//小程序登录
//    	UNFILTER_URI.add("/interface/websiteNews/websiteNewsSharList");//获取新闻详情
    	
//    	 UNFILTER_URI.add("/captchaImage");				//获取验证码
//    	 UNFILTER_URI.add("/login");       				//登录页面 
//         UNFILTER_URI.add("/north");       				//首页-区域
//         UNFILTER_URI.add("/west");        				//首页-区域
//         UNFILTER_URI.add("/center");      				//首页-区域
//         UNFILTER_URI.add("/south");       				//首页-区域
//         UNFILTER_URI.add("/home");        				//首页-区域
//         UNFILTER_URI.add("/sys/user/admin/login");     //首页-区域
//    	 UNFILTER_URI.add("/sys/user/getUsers");		//获取用户列表
//    	 
//    	 UNFILTER_URI.add("/jsp/pages/purchaser/login.jsp");		//客户端登录
//    	 UNFILTER_URI.add("/jsp/pages/purchaser/index.jsp");		//客户端首页
//    	 UNFILTER_URI.add("/sys/user/client/login");     			//客户端登录
//    	 UNFILTER_URI.add("/sys/user/client/register");  			//客户端注册
//    	 
//    	 UNFILTER_URI.add("/busi/phonecall/rly/callback");//容联云双向回拨电话账单回调
//    	 UNFILTER_URI.add("/busi/phonecall/rly/callback/CallAuth");//容联云双向回拨电话鉴权回调
//    	 UNFILTER_URI.add("/allInPay/resultPay");//通联支付回调
//    	  	 
//    	 //不拦截：开头匹配的请求
//    	 UNFILTER_URI_STARTWITH.add("/resources");//资源文件
//    	 UNFILTER_URI_STARTWITH.add("/static");//资源文件
    	UNFILTER_URI_STARTWITH.add("/res");//资源文件
    }
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}

    /**
     * preHandle (HttpServletRequest request, HttpServletResponse response, Object handle) 方法，
     * 顾名思义，该方法将在请求处理之前进行调用。
     * SpringMVC 中的Interceptor 是链式的调用的，
     * 在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。
     * 每个Interceptor 的调用会依据它的声明顺序依次执行，
     * 而且最先执行的都是Interceptor 中的preHandle 方法，
     * 所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理，
     * 也可以在这个方法中进行一些判断来决定请求是否要继续进行下去。
     * 该方法的返回值是布尔值Boolean 类型的，
     * 当它返回为false 时，表示请求结束，
     * 后续的Interceptor 和Controller 都不会再执行；
     * 当返回值为true 时就会继续调用下一个Interceptor 的preHandle 方法，
     * 如果已经是最后一个Interceptor 的时候就会是调用当前请求的Controller 方法。
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		String servletPath = request.getServletPath();      //获取请求的链接
		 //获取用户
//		 System.out.println("拦截到请求："+servletPath);
		if (unsign(servletPath)){ return true;}   //开始匹配
		if (unfilter(servletPath)){ return true;} //完全匹配
		//获取登录标识
		String sessionId = request.getParameter("sessionId"); //请求是否来自客户端的标识
		if(StringUtil.isNotEmpty(sessionId)){
			 if (usersTmplService == null) {
				 usersTmplService = SpringBeanManager
						.getBean(UsersTmplService.class);
			}
			 HttpSession session = request.getSession();
			 UsersTmpl usersTmpl = (UsersTmpl) session.getAttribute(sessionId);
			 if(usersTmpl == null){
				 usersTmpl = usersTmplService.getUserBySessionId(sessionId);
				 if(usersTmpl == null){//如果查询不到登录信息
					 response.setCharacterEncoding("UTF-8"); 
		        		response.setContentType("text/json"); 
		        		PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
		        		try{
//		        			Result result = new Result();
//		        			result.setError(ResultCode.CODE_STATE_4004, "抱歉，用户信息错误，请重试或重新登录重试！");
		        			JSONObject obj = new JSONObject();
		        		obj.put("success", false);
//		        		obj.put("logout", true);
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
				 session.setAttribute(sessionId, usersTmpl);
			 }
			 
		}else{
			PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
    		try{
//    			Result result = new Result();
//    			result.setError(ResultCode.CODE_STATE_4004, "抱歉，用户信息错误，请重试或重新登录重试！");
    			JSONObject obj = new JSONObject();
    		obj.put("success", false);
//    		obj.put("logout", true);
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
        return true;
	}

	/**
	 * 
	 * @param msgCode   对应消息码
	 * @param msg       返回消息
	 * @param request   请求对象
	 * @param response  响应对象
	 * @throws ServletException  
	 * @throws IOException
	 */
	 private void forward(Integer msgCode, String msg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setAttribute("msg", msg);
	        if (msgCode == ResultCode.CODE_STATE_4002) {
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
	        } else {
	            request.getRequestDispatcher("/jsp/error/authMsg.jsp").forward(request, response);
	        }
	 }
	 
	 /**
	     * 这些不拦截
	     *
	     * @param servletPath
	     * @return
	     */
	    private boolean unfilter(String servletPath) {
	        if (UNFILTER_URI.contains(servletPath)) {
	            return true;
	        }
	        return false;
	    }

	    private boolean unsign(String servletPath) {
	        for (String path : UNFILTER_URI_STARTWITH) {
	            if (servletPath.startsWith(path)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    
}
