package main.com.Interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import main.com.frame.base.SpringBeanManager;
import main.com.frame.domain.ResultCode;
import main.com.system.dao.po.SystemUsers;
import main.com.system.service.ManagerMenuService;
import main.com.system.service.SystemUsersService;
import main.com.utils.StringUtil;
import main.com.weixinHtml.dao.po.ShopUsers;
import main.com.weixinHtml.service.ShopUserService;
import net.sf.json.JSONObject;

public class ShopInterfaceInterceptor  implements HandlerInterceptor{

	  @Autowired
	  private SystemUsersService systemUsersService; //这里使用@Autowired无法注入成功 
	  
		@Autowired
		private ShopUserService shopUserService;
	  
	  @Autowired
	  private ManagerMenuService managerMenuService; //设计说了菜单要即配即生效，考虑到这是后台，用户数不会很大，so........
	  
	private static final Logger logger = LoggerFactory.getLogger(ManagerInterceptor.class);
	
	// 这些请求不拦截
  private static final Set<String> UNFILTER_URI = new HashSet<>();
  // 这些开头的请求不拦截
  private static final Set<String> UNFILTER_URI_STARTWITH = new HashSet<>();
  
  static {
  	 //不拦截：完全匹配的请求
  	 UNFILTER_URI.add("/interfaceShop/shopUsers/loginPassword");	
  	 UNFILTER_URI.add("/interfaceShop/shopUsers/loginCode");	
  	 UNFILTER_URI.add("/interfaceShop/shopUsers/silenceLogin");	
  	 UNFILTER_URI.add("/interfaceShop/shopUsers/loginOut");	
  	 UNFILTER_URI.add("/interfaceShop/shopUsers/register");
  	 UNFILTER_URI.add("/interfaceShop/shopUsers/forgetPassword");
  	 UNFILTER_URI.add("/interfaceShop/shopGoodsCars/shopGoodsCarsList");
  	 UNFILTER_URI.add("/interfaceShop/shopGoodsCars/shopGoodsCarsInfo");
  	 UNFILTER_URI.add("/interfaceShop/shopGoodsCars/fullPayment");
  	 UNFILTER_URI.add("/interfaceShop/shopGoodsCars/loanPayment");
  	 UNFILTER_URI.add("/interfaceShop/shopGoodsCars/brandListList");
  	 UNFILTER_URI.add("/interfaceShop/shopGoodsCars/cityListList");
  	 UNFILTER_URI.add("/interfaceShop/goodsCarsActivity/activityList");
  	 UNFILTER_URI.add("/interfaceShop/goodsCarsActivity/activityInfo");
  	 UNFILTER_URI.add("/interfaceShop/consignment/expensesCount");
  	 UNFILTER_URI.add("/interfaceShop/consignment/logisticsLineList");
  	 UNFILTER_URI.add("/interfaceShop/shopIndex/orgInfo");
//  	 UNFILTER_URI.add("/interfaceShop/shareMaterialInfo/shareMaterialInfoSharHtml");
  	 //不拦截：开头匹配的请求
  	 UNFILTER_URI_STARTWITH.add("/resources");//资源文件
  	 UNFILTER_URI_STARTWITH.add("/static");//资源文件
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
//		isClient = "Y";
//		System.out.println("================"+servletPath+"================");
		if (unsign(servletPath)){ return true;}   //开始匹配
		if (unfilter(servletPath)){ return true;} //完全匹配
		//处理登录信息
		String sessionId = request.getParameter("sessionId"); //请求是否来自客户端的标识
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
		 
		 SystemUsers systemUsers = null;
		 ShopUsers shopUser = null;
		try {
			systemUsers = (SystemUsers) session.getAttribute(sessionId);
		} catch (Exception e1) {//如果强制转换失败
			shopUser = (ShopUsers) session.getAttribute(sessionId);
		}
		 if(systemUsers == null){
				if (systemUsersService == null) {
					 systemUsersService = SpringBeanManager.getBean(SystemUsersService.class);
				}
			systemUsers = systemUsersService.getByCode(sessionId);
			 if(systemUsers == null){//如果查询不到登录信息
					if(shopUserService == null) {
						shopUserService = SpringBeanManager.getBean(ShopUserService.class);
					}
					if(shopUser == null) {
//						shopUser = shopUserService.getById(20);
						shopUser = shopUserService.getByCode(sessionId);
					}
					if(shopUser == null) {
						 response.setCharacterEncoding("UTF-8"); 
			        		response.setContentType("text/json"); 
			        		PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
			        		try{
//			        			Result result = new Result();
//			        			result.setError(ResultCode.CODE_STATE_4004, "抱歉，用户信息错误，请重试或重新登录重试！");
			        			JSONObject obj = new JSONObject();
			        		obj.put("success", false);
//			        		obj.put("logout", true);
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
					}else {
						session.setAttribute(sessionId, shopUser);
					}
			 }else {
				 session.setAttribute(sessionId, systemUsers);
			 }
		 }
		 //检查是否有访问此菜单的权限
//		System.out.println("================"+servletPath+"================");
		 
		return true;
//		 先关闭菜单检测
//		List<MenuVo> menus = managerMenuService.getUserRoleMenu(systemUsers.getUsersId());
//		if(menus == null || menus.size() <= 0) {
//			response.setCharacterEncoding("UTF-8"); 
//  		response.setContentType("text/json"); 
//  		PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
//  		try{
//  			JSONObject obj = new JSONObject();
//  		obj.put("success", false);
//  		obj.put("resultCode", ResultCode.CODE_STATE_4002);
//			obj.put("message", "抱歉，你没有此系统操作项的任何相关权限，如需授权，请联系管理员");
//			out.println(obj);
//			out.flush();
//  		}catch(Exception e){
//  			e.printStackTrace();
//  		}finally{
//  			out.close();
//  		}
//  	 return false;
//		}
//		List<String> list = new ArrayList<String>();
//		for(MenuVo menuVo : menus) {
//			list.add(menuVo.getSrc());
//		}
//		if (list.contains(servletPath)) {
//          return true;
//      }else {
//      	response.setCharacterEncoding("UTF-8"); 
//  		response.setContentType("text/json"); 
//  		PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
//  		try{
//  			JSONObject obj = new JSONObject();
//  		obj.put("success", false);
//  		obj.put("resultCode", ResultCode.CODE_STATE_4005);
//			obj.put("message", "抱歉，你没有此操作项的相关权限，如需授权，请联系管理员");
//			out.println(obj);
//			out.flush();
//  		}catch(Exception e){
//  			e.printStackTrace();
//  		}finally{
//  			out.close(); 
//  		}
//  	 return false;
//      }
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
//	        	if("/interfaceShop/shopGoodsCars/shopGoodsCarsList".equals(servletPath)) {
//	        		return false;//对来自商户端的
//	        	}
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
