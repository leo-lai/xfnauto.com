package main.com.frame.constants;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator; 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer; 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider; 
 
/**
 * 全局变量，不可删除
* @description: 转换null对象为空字符串
* 把全部的返回带有的null装换为空字符串
*/ 
public class JsonObjectMapper extends ObjectMapper { 
  private static final long serialVersionUID = 1L; 
 
  public JsonObjectMapper() { 
      super(); 
      // 空值处理为空串 
      this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { 
          @Override 
          public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException { 
              jg.writeString(""); 
          } 
      }); 
      
    //设置null值不参与序列化(字段不被显示)    
//      this.setSerializationInclusion(Include.NON_NULL);  
        
      // 禁用空对象转换json校验  
//      this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);  
      this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
      //驼峰命名法转换为小写加下划线  
//      this.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);  
  } 
} 
