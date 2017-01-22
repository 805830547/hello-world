package com.sixstar.mvc.core;
/**
 * 
* @ClassName: IdentifyView  
* @Description: Mvc框架返回的核心视图和数据  
* @date 2016年11月28日 下午9:08:35  
*
 */
public class IdentifyView {
	private ResultContent resultContent;
	private ResultType resultType;
	public IdentifyView(ResultContent resultContent){
		this(resultContent,ResultType.FORWORD);
	}
	public IdentifyView(String viewName){
		resultContent = new ResultContent(viewName);
		resultType = ResultType.FORWORD;
	}
	public IdentifyView(ResultContent resultContent,ResultType resultType){
		this.resultContent = resultContent;
		this.resultType = resultType;
	}
	public ResultContent getResultContent() {
		return resultContent;
	}
	public void setResultContent(ResultContent resultContent) {
		this.resultContent = resultContent;
	}
	public ResultType getResultType() {
		return resultType;
	}
	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}
}
