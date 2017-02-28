package com.sixstar.mvc.core;
/**
 *
 * @ClassName: ResultType
 * @Description: MVC框架返回值类型
 *
 */
public enum ResultType {
    //请求转发
    FORWORD,
    //重定向
    REDIRECT,
    //Ajax异步请求
    AJAX,
    //请求转发到新的控制器  /WEB-INF/VIEWS/INDEX.JSP  login.do index.do
    FORWORDCHAIN
}
