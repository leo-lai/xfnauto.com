package main.com.frame.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识记录日志信息的注解
 * 
 *
 */
@Target(ElementType.METHOD)    
@Retention(RetentionPolicy.RUNTIME)    
@Documented
public @interface LogPoint {
	
	String operateModule() default "";

	String logDes() default "";
}
