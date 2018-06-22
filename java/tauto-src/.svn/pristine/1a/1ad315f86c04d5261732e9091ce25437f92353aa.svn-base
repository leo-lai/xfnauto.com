/**
 * $Id: XmlUtils.java Nov 20, 2014 3:25:01 PM hdp
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.com.utils.weixin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>创建时间: Nov 20, 2014 3:25:01 PM</p>
 * @author <a href="mailto:hongdanping@163.com">hdp</a>
 * @version V1.0
 * @since V1.0
 */
public final class XmlUtils {
	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(XmlUtils.class);
	
	private XmlUtils() {
		throw new RuntimeException("can't init");
	}
	/**
	 * 作用：array转xml
	 * @param map
	 * @return
	 */
	public static String mapToXml(Map<String, String> map) {
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		String value = null;
		for (String key : map.keySet()) {
			value = map.get(key);
			xml.append("<").append(key).append(">");
			if (StringUtils.isNumeric(value)) {
				xml.append(value);
			} else {
				xml.append("<![CDATA[").append(value).append("]]>");
			}
			xml.append("</").append(key).append(">");
		}
		xml.append("</xml>");
        return xml.toString(); 
    }
	/**
	 * 作用：将xml转为map
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> xmlToMap(String xml) {		
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			LOG.error(e.getMessage(), e);
		}
		if (null == doc) return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			map.put(e.getName(), e.getText());
		}
		return map;
	}
}
