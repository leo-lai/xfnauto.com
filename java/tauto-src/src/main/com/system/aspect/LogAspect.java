package main.com.system.aspect;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qiniu.util.Base64;

import main.com.frame.annotation.LogPoint;
import main.com.frame.domain.Result;
import main.com.system.dao.po.LogOperate;
import main.com.system.dao.po.SessionInfo;
import main.com.system.dao.po.SystemUsers;
import main.com.system.service.SysLogService;
import main.com.utils.Base64Util;
import main.com.utils.UUIDUtils;


/**
 * 记录日志的切面
 * 
 * 
 */
@Aspect
@Component
public class LogAspect {

	@Autowired 
	HttpServletRequest request; //这里可以获取到request
	@Autowired
	private SysLogService logService;
	
	public LogAspect() {
//		System.out.println("日记初始化");
	}
	
    //Controller层切点    
//    @Pointcut("@annotation(main.com.frame.annotation.LogPoint)")
//     public  void controllerAspectA() {
//    }    
    
    @Pointcut("execution(* main.com.*.controller..*.*(..))")  
    private void controllerAspect(){}//定义一个切入点
	
//    @After("controllerAspectA()")
//    public void addOperateLogFromDAO(JoinPoint jp) {
//    	System.out.println("拦截日志执行1");
//    }
//    @After("execution(* main.com.system.controller.ManagerUserController.addUser(..))")
//    public void addOperateLogFromDAOBA(JoinPoint jp) {
//    	System.out.println("拦截日志执行BA");
//    }
//    @Before("controllerAspect()")
//    public void addOperateLogFromDAOB(JoinPoint jp) {
//    	System.out.println("拦截日志执行2");
//    }
//    @After("controllerAspectB()")
//    public void addOperateLogFromDAOC(JoinPoint jp) {
//    	System.out.println("拦截日志执行3");
//    }
//    @Before("controllerAspectB()")
//    public void addOperateLogFromDAOD(JoinPoint jp) {
//    	System.out.println("拦截日志执行4");
//    }
    
	private static final Logger logger = Logger.getLogger(LogAspect.class);
	
//	 @AfterReturning(value="controllerAspect()",returning="result")
//	    public void afterReturnMethod(JoinPoint joinPoint,Result result){
//	        String methodName = joinPoint.getSignature().getName();
//	        System.out.println("this method "+methodName+" end.result<"+result+">");
//	 }
	
//	@After("controllerAspect()")
	@AfterReturning(value="controllerAspect()",returning="result")
	public void addOperateLogFromDAO(JoinPoint jp,Result result) {
		String targetMethodName = jp.getSignature().getName();
//		Class<?> daoInterface = null;     //匹配的接口
		LogPoint logPoint = null;
		try {
			logPoint = getControllerMethodLogPoint(jp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}         //需要记录日志的地方        
//		for (Class<?> iface : jp.getTarget().getClass().getSuperclass().getInterfaces()) {
//			try {
//				for (Method method : iface.getDeclaredMethods()) {
//					if (method.getName().equals(targetMethodName)) {
//						if (method.isAnnotationPresent(LogPoint.class)) {
//							logPoint = method.getAnnotation(LogPoint.class);
//							daoInterface = iface;
//							break;
//						}
//					}
//				}
//			} catch (Exception e) {
//				logger.error(iface + "中没有目标方法(logAspect)", e);
//			}
//		}
		if (logPoint != null && result != null && result.isSuccess()) {//有日志标识点，需要保存日志
			String operUserId = "0";
			String operUserName = "用户";
//			SystemUsers systemUsers = null;
			//保存日志
			LogOperate logOperate = new LogOperate();
			logOperate.setLogId(UUIDUtils.create());
			logOperate.setLogDate(new Date());
			logOperate.setClientUrl(request.getRemoteAddr()+":"+request.getRemotePort());
			logOperate.setAccessUrl(request.getServletPath());
			JSONObject json = JSONObject.fromObject(request.getParameterMap());
			logOperate.setAccessParams(json.toString());
			if(json.has("sessionId")) {
				String sessionId = json.get("sessionId").toString();
				sessionId = sessionId.substring(2, sessionId.length()-2);
//				systemUsers = (SystemUsers) request.getSession().getAttribute(sessionId);
				logOperate.setOperSessionId(sessionId);
			}
			if(json.has("password")) {//覆盖密码，个人资料保密
				json.put("password", "******");
			}
//			if(systemUsers != null){
//				operUserId = systemUsers.getUsersId()+"";
//				operUserName = systemUsers.getUserName();
//			}
			logOperate.setOperUserId(operUserId);
			logOperate.setOperUserName(operUserName);
			logOperate.setOperMethod(targetMethodName);
			logOperate.setIpAddress(getIpAddr()); 
			String operDetail = this.parseParames(jp.getArgs());
			logOperate.setOperDetail(Base64Util.encodeData(operDetail));
			logOperate.setLogDes(logPoint.logDes()+"--"+logPoint.operateModule());
			try {
				this.logService.addOperateLog(logOperate);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(targetMethodName+"---------------------报错");
				logger.error(e.getMessage());
			}
		}else {
//			System.out.println("没有包含拦截切点");
		}
	}
	
	  /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static LogPoint getControllerMethodLogPoint(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        LogPoint logPoint = null;    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {  
                	 logPoint = method.getAnnotation(LogPoint.class);          
                     break;    
                }    
            }    
        }    
         return logPoint;
    }    
	
	/**
	 * 解析方法参数
	 * 
	 * @param parames
	 *            方法参数
	 * @return 解析后的方法参数
	 */
	private String parseParames(Object[] parames) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < parames.length; i++) {
			if (parames[i].getClass().getName().indexOf("com.system") != -1) {
				if (parames[i] instanceof Object[]
						|| parames[i] instanceof Collection) {
					JSONArray json = JSONArray.fromObject(parames[i]);
					if (i == parames.length - 1) {
						sb.append(json.toString());
					} else {
						sb.append(json.toString() + ",");
					}
				} else {
					JSONObject json = JSONObject.fromObject(parames[i]);
					if (i == parames.length - 1) {
						sb.append(json.toString());
					} else {
						sb.append(json.toString() + ",");
					}
				}

			} else if (parames[i] instanceof String) {
				if (i == parames.length - 1) {
					sb.append(parames[i]);
				} else {
					sb.append(parames[i] + ",");
				}
			} else if (parames[i] instanceof Object[]
					|| parames[i] instanceof Collection) {
				JSONArray json = JSONArray.fromObject(parames[i]);
				if (i == parames.length - 1) {
					sb.append(json.toString());
				} else {
					sb.append(json.toString() + ",");
				}
			}

		}
		String params = sb.toString();
		params = params.replaceAll("(\"\\w+\":\"\",)", "");
		params = params.replaceAll("(,\"\\w+\":\"\")", "");
		return params;
	}

	protected String getIpAddr() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
