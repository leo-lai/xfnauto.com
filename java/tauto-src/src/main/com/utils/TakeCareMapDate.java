package main.com.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanMap;

import net.sf.json.JSONObject;

/**
 * 针对Map集合的数据处理
 * @author Zwen
 *
 */
public class TakeCareMapDate{

	/**
	 * 把Map集合里的null转换为""
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> cutNullMap(Map<String, Object> map) {
		TreeMap<String,Object> params = new TreeMap<String, Object>();
		if (map == null) {
			return params;
		}
//		BeanMap beanMap = new BeanMap(obj);
//		Set keys = beanMap.keySet();
//		Iterator it = keys.iterator();
//		while (it.hasNext()) {
//			String name = it.next()+"";
//			Object value = beanMap.get(name);
//			// 转换时会将类名也转换成属性，此处去掉
//			if(name.equals("class")) {
//				continue;
//			}
//			if (value == null) {
//				params.put(name, "");
//			}else if (value != null){
//				params.put(name, value+"");
//			}
//		}
//		return params;
		
		Iterator<Entry<String, Object>> it=map.entrySet().iterator();  
        while(it.hasNext()){  
            Map.Entry<String, Object> entry=it.next();  
            Object value = entry.getValue();
            if (value == null) {
				params.put(entry.getKey(), "");
			}else if (value != null){
				params.put(entry.getKey(), value);
			}
        }  
        return params;
	}

}
