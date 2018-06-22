package main.com.Interceptor;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import main.com.frame.domain.ResultCode;
import net.sf.json.JSONObject;

/**
 * 全局拦截非法字符
 * @author Zwen
 *
 */
public class CharacterInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
         //获得所有请求参数名  
         Enumeration params = request.getParameterNames();  
         String sql = "";  
        while (params.hasMoreElements()) {  
             //得到参数名  
             String name = params.nextElement().toString();  
             //System.out.println("name===========================" + name + "--");  
             //得到参数对应值  
             String[] value = request.getParameterValues(name);  
             for (int i = 0; i < value.length; i++) {  
                 sql = sql + value[i];  
             }  
         }  
//        System.out.println("被匹配字符串："+sql);  
         if (sqlValidate(sql)) {  
        	 PrintWriter out =response.getWriter();//提取出来以后报错，所以每次使用都生成一个
      		try{
      			JSONObject obj = new JSONObject();
      			obj.put("success", false);
          		obj.put("resultCode", ResultCode.CODE_STATE_4005);
      			obj.put("message", "您的请求包含有不合法信息，可能是由于包含;/-/--/+,/like//////%/#等系列非法字符，请修改后重新操作");
      			out.println(obj);
      			out.flush();
      		}catch(Exception e){
      			e.printStackTrace();
      		}finally{
      			out.close();
      		}
          return false;
          } else {
        	  return true;
          }
     }  
   
     //校验
     protected static boolean sqlValidate(String str) {  
         str = str.toLowerCase();//统一转为小写
         //String badStr = "and|exec";
//         String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|chr|mid|master|truncate|char|declare|sitename|net user|xp_cmdshell|or|like";  
         String badStr = "'|and|exec|execute|insert|create|drop|table|from|grant|use|group_concat|column_name|" +  
                 "information_schema.columns|table_schema|union|where|select|update|by|count|*|" +  
                 "chr|mid|master|truncate|char|declare|or|--|+|like|%|#|null";    //过滤掉的sql关键字，可以手动添加  
         String[] badStrs = badStr.split("\\|");  
         for (int i = 0; i < badStrs.length; i++) {
//             if (str.indexOf(badStrs[i]) !=-1) { 
//                 System.out.println("匹配到："+badStrs[i]);
//                 return true;  
//             }  
             if (str.equals(badStrs[i].trim())) {
                 System.out.println("匹配到："+badStrs[i]);
                 return true;  
             }  
         }  
         return false;  
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
