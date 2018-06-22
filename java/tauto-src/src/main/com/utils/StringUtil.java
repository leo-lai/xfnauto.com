package main.com.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空：
	 * true: null 或 ''
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(String str){
		if(str == null || "".equals(str) || str.equals("null")){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否为空：
	 * true: null 或 ''
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(Integer number){
		if(number == null || number <= 0){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否为空：
	 * true: null 或 ''
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(Double number){
		if(number == null || number <= 0d){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否为空：
	 * true: null 或 ''
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(Long number){
		if(number == null || number <= 0l){
			return true;
		}
		return false;
	}
	
	/**
	 * 把null转化为空字符串
	 * true: null 或 ''
	 * @param str
	 * @return
	 */
	public static String toEmpty(String str){
		if(str == null){
			return "";
		}
		return str;
	}
	
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(String str){
		if(str != null && !"".equals(str)){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(Long str){
		if(str != null && str >= 0l){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmptyMoreThenZero(Long str){
		if(str != null && str > 0l){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmptyMoreThenZero(Double str){
		if(str != null && str > 0d){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(Integer number){
		if(number != null && number >= 0){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(Float number){
		if(number != null && number >= 0.0f){
			return true;
		}
		return false;
	}
	/**
	 * 判断字符串是否不为空：
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(Double number){
		if(number != null && number >= 0.0d){
			return true;
		}
		return false;
	}
	/**
	 * 判断list是否为空
	 */
	public static Boolean isEmptyList(List list){
		if(null == list || list.size() == 0)
			return true;
		return false;
	}
	/**
	 * 字符串分隔成数组
	 * @param 要分隔的字符串
	 * @param 分隔符
	 */
	public static String[] split(String str,String seprator){
		if(null != str){
			//如果字符串最后一个字符是“，”字符串分隔的时候会把“，”后面的空字符串不当做数组元素。所以在后面手动加一个0
			if(str.endsWith(seprator)){
				str+="0";
				String [] arr = str.split(seprator);
				arr[arr.length-1] = "";     //重新把手动添加的那个0替换为空字符串
				return arr;
			}else
				return str.split(seprator);
		}
		return null;
	}
	/**
	 * 字符串分隔成List
	 * @param 要分隔的字符串
	 * @param 分隔符
	 */
	public static List<Integer> splitStringsToList(String strings,String index){
		if(null == strings || "".equals(strings)){
			return new ArrayList<Integer>();
		}
		String[] str = strings.split(index);
		List<Integer> list = new ArrayList<Integer>();
		for(String string : str){
			//判断是否为空，并且是数字
			if(!"".equals(string) && null != string && isNumeric(string)){
				list.add(Integer.parseInt(string));
			}
		}
		return list;
	}
	/**
	 * 字符串分隔成数组
	 * @param 要分隔的字符串
	 * @param 分隔符
	 */
	public static Integer[] splitStringsToInteger(String strings,String index){
		if(null == strings || "".equals(strings)){
			return new Integer[]{};
		}
		String[] str = strings.split(index);
		Integer [] arr = new Integer[str.length];
		for(int i = 0;i<str.length;i++){
			//判断是否为空，并且是数字
			if(!"".equals(str[i]) && null != str[i] && isNumeric(str[i])){
				arr[i] = Integer.parseInt(str[i]);
			}
		}
		return arr;
	}
	/**
	 * 判断字符是不是中文
	 */
	public static boolean isChinese(char c){
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
            return true;
        }
		return false;
	}
	/**
	 * 判断字符串中是否包含中文
	 */
	public static boolean isContainChinese(String str){
		boolean res=false;
        char [] cTemp = str.toCharArray(); 
        for(int i=0;i<str.length();i++)
        {
                if(isChinese(cTemp[i]))
                {
                        res=true;
                        break;
                }
        }           
        return res;
	}
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		Pattern pattern = Pattern.compile("[0-9]*"); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
		    return false; 
		} 
		return true; 
	}
	
	/**
	 * 屏蔽电话号
	 * @param str
	 * @return
	 */
	public static String shieldPhone(String phoneNumber){ 
		if(isEmpty(phoneNumber)){
			return phoneNumber;
		}
		return phoneNumber.substring(0,3) + "****" + phoneNumber.substring(7, phoneNumber.length());
	}
	public static boolean isEmpty(Boolean isMortgage) {
		if(isMortgage == null) {
			return true;
		}
		return false;
	}
	/**
	 * 屏蔽姓名
	 * @param str
	 * @return
	 */
	public static String shieldName(String name){ 
		if(isEmpty(name)){
			return name;
		}
		return name.substring(0,1) + "**" ;
	}
	
	public static boolean isEmpty(BigDecimal guidancePrice) {
		if(guidancePrice == null) {
			return true;
		}
		if(guidancePrice.doubleValue() <= 0d) {
			return true;
		}
		return false;
	}
	public static boolean isNotEmptyMoreThenZero(Integer number) {
		if(number != null && number > 0){
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(BigDecimal offerAmount) {
		if(offerAmount != null && offerAmount.compareTo(new BigDecimal(0)) > 0){
			return true;
		}
		return false;
	}
	public static boolean isNotEmptyAll(BigDecimal offerAmount) {
		if(offerAmount != null){
			return true;
		}
		return false;
	}
}
