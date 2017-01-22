package com.sixstar.mvc.core;
import com.alibaba.fastjson.JSON;
/**
 * 
* @ClassName: ResultContent  
* @Description: 返回结果内容 
 */
public class ResultContent {
	/**
	 * 返回需要跳转地址
	 */
	private String url;
	/**
	 * 需要返回的Json对象
	 */
	private Object obj;
	
	public ResultContent(String url){
		this.url = url;
	}
	public ResultContent(Object obj){
		this.obj = obj;
	}
	public String getUrl() {
		return url;
	}
	public String getJson(){
		return JSON.toJSONString(obj);
	}
	public Object getObj() {
		return obj;
	}
	
}
