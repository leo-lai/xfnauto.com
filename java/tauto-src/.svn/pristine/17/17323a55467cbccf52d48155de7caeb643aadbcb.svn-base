package main.com.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StringCode {

	public static String getOrderCode(String phone){
			String befor = "DD";
		 int validCode = (int) ((Math.random() * 9 + 1) * 1000);//四位随机数
		 String code = DateUtil.formatCodeOnleyDate(new Date());
		 String phoneCode = "";
		 if(StringUtil.isEmpty(phone)) {
			 phoneCode = getRandomCodes(4);
		 }else {
			 phoneCode = phone.substring(phone.length()-4, phone.length());
		 }
		return befor+code+phoneCode+validCode;
	}
	
	public static String getWithdrawalCode(String phone) {
		if(StringUtil.isEmpty(phone)){
			phone = "1388888888";
		}
		String befor = "TX";
		int validCode = (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
		String code = DateUtil.formatCodeOnleyDate(new Date());
		String phoneCode = phone.substring(phone.length() - 4, phone.length());
		return befor + code + phoneCode + validCode;
	}
	public static String getEyeWithdrawalCode() {
		String befor = "ETX";
		int validCode = (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
		String code = DateUtil.formatCodeOnleyDate(new Date());
		return befor + code + validCode;
	}
	
	public static String getRebateRecordCode(String phone) {
		String befor = "FL";
		int validCode = (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
		String code = DateUtil.formatCodeOnleyDate(new Date());
		String phoneCode = phone.substring(phone.length() - 4, phone.length());
		return befor + code + phoneCode + validCode;
	}
	
	public static String getRemittanceCode(String phone) {
		String befor = "DC";
		int validCode = (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
		String code = DateUtil.formatCodeOnleyDate(new Date());
		String phoneCode = phone.substring(phone.length() - 4, phone.length());
		return befor + code + phoneCode + validCode;
	}
	
	public static String getDeliveryCode(String phone) {
		String befor = "TH";
		int validCode = (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
		String code = DateUtil.formatCodeOnleyDate(new Date());
		String phoneCode = phone.substring(phone.length() - 4, phone.length());
		return befor + code + phoneCode + validCode;
	}
	
	public static String getAdvanceOrderCode(String phone) {
		String befor = "YY";
		int validCode = (int) ((Math.random() * 9 + 1) * 1000);// 四位随机数
		String code = DateUtil.formatCodeOnleyDate(new Date());
		String phoneCode = phone.substring(phone.length() - 4, phone.length());
		return befor + code + phoneCode + validCode;
	}
	
	public static String getProblemCode(){
	String befor = "WZ";
	 int validCode = (int) ((Math.random() * 9 + 1) * 1000);//四位随机数
	 String code = DateUtil.formatCodeOnleyDate(new Date());
	return befor+validCode+code;
	}

	 public static String getOtherCodes(int length,String codeBefo){
//	    List<String> results=new ArrayList<String>();
	      String val = ""; 
	      String beforCode = codeBefo+DateUtil.formatCode(new Date());
	    for(int j=0;j<length;j++){
	      Random random = new Random();   
//	      for(int i = 0; i < length; i++){
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字   
	        if("char".equalsIgnoreCase(charOrNum)) // 字符串   
	        {
	          int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母   
	          val += (char) (choice + random.nextInt(26));
	        }
	        else if("num".equalsIgnoreCase(charOrNum)) // 数字   
	        {
	          val += String.valueOf(random.nextInt(10));   
	        }
	      }
//	      val=val.toLowerCase();
//	      if(results.contains(val)){
//	        continue;
//	      }else{
//	        results.add(val);
//	      }
//	    }
	    return beforCode + val;
	}  
	 
	 public static String getRandomCodes(int length){
//	    List<String> results=new ArrayList<String>();
		 String val = "";   
		 for(int j=0;j<length;j++){
			 Random random = new Random();   
//	      for(int i = 0; i < length; i++){
			 String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字   
			 if("char".equalsIgnoreCase(charOrNum)) // 字符串   
			 {
				 int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母   
				 val += (char) (choice + random.nextInt(26));
			 }
			 else if("num".equalsIgnoreCase(charOrNum)) // 数字   
			 {
				 val += String.valueOf(random.nextInt(10));   
			 }
		 }
//	      val=val.toLowerCase();
//	      if(results.contains(val)){
//	        continue;
//	      }else{
//	        results.add(val);
//	      }
//	    }
		 if(StringUtil.isEmpty(val)) {
			 getRandomCodes(length);
		 }
		 return val.toUpperCase();
	 }  
	 
	 public static String getUserCodes(int length,Boolean isNumber){
//	    List<String> results=new ArrayList<String>();
		 String val = "";   
		 for(int j=0;j<length;j++){
			 Random random = new Random();   
//	      for(int i = 0; i < length; i++){
//			 String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字   
//			 if("char".equalsIgnoreCase(charOrNum)) // 字符串   
//			 {
//				 int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母   
//				 val += (char) (choice + random.nextInt(26));
//			 }
//			 else if("num".equalsIgnoreCase(charOrNum)) // 数字   
//			 {
				 val += String.valueOf(random.nextInt(10));   
//			 }
		 }
//	      val=val.toLowerCase();
//	      if(results.contains(val)){
//	        continue;
//	      }else{
//	        results.add(val);
//	      }
//	    }
		 return val;
	 }  
	 
	 public static void main(String[] args) {
		 System.out.println(StringCode.getUserCodes(6,true));
	}

	public static String getUserCodes(Integer length) {
		 String val = "";   
		 for(int j=0;j<length;j++){
			 Random random = new Random();   
			 String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字   
			 if("char".equalsIgnoreCase(charOrNum)) // 字符串   
			 {
				 int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母   
				 val += (char) (choice + random.nextInt(26));
			 }
			 else if("num".equalsIgnoreCase(charOrNum)) // 数字   
			 {
				 val += String.valueOf(random.nextInt(10));   
			 }
		 }
		 if(StringUtil.isEmpty(val)) {
			 getRandomCodes(length);
		 }
		 return val.toUpperCase();
	}
}
