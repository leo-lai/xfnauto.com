package main.com.utils.rlycode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		if (str == null) {
			return false;
		}
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		// p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		p = Pattern
				.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|7|8]|18[0-9])\\d{8}$");
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		if (str == null) {
			return false;
		}
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		// p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		p = Pattern.compile("^\\d{11}$");
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
   /* public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    /** 
     * 电话号码验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
   /* public static boolean isPhone(String str) {   
        Pattern p1 = null,p2 = null;  
        Matcher m = null;  
        boolean b = false;    
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的  
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的  
        if(str.length() >9)  
        {   m = p1.matcher(str);  
            b = m.matches();    
        }else{  
            m = p2.matcher(str);  
            b = m.matches();   
        }    
        return b;  
    }*/  
}
