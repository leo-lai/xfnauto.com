package main.com.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Number {

	public static double doubleFormat(Double format) {
		if(format == null){
			return 0.00d;
		}
		BigDecimal b = new BigDecimal(format+"");
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	 
	/**
	 * 只能是正整数
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isGoodNumber(String str) {
		if (str == null) { 
		    return false; 
		  } 
		  int sz = str.length(); 
		  for (int i = 0; i < sz; i++) { 
		    if (Character.isDigit(str.charAt(i)) == false) { 
		      return false; 
		    } 
		  } 
		  return true; 
	}
	
	public static double getCeil(double d,int n){
		BigDecimal b = new BigDecimal(String.valueOf(d));
		b = b.divide(BigDecimal.ONE,n,BigDecimal.ROUND_CEILING);
		return b.doubleValue();
	}
	
	public static void main(String[] args) {
		System.out.println(Number.isGoodNumber("1.5"));
	}
}
