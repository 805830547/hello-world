package com.sixstar.mvc.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
* @ClassName: RequestMapping  
* @Description: 设置请求路径的注解  
* @date 2016年11月23日 下午9:19:38  
*
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface RequestMapping {
	public String value();
}
