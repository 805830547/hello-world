package com.sixstar.mvc.core;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 *
 * @ClassName: BaseController
 * @Description: 基础控制器
 * @date 2016年11月23日 下午9:16:12
 *
 */
public class BaseController {
    protected Logger logger  = Logger.getLogger(BaseController.class);
    /**
     * 1.控制器的类上面应该使用@RequestMapping 设置这个Controller类的路径
     * 2.控制器的方法上使用@RequestMapping 设置这个控制器下第二层的请求路径    类的路径/方法的请求路径.do
     * 3.定义一个@RequestMapping的注解
     */
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    /**
     *
     * @Title: init
     * @Description:提供给DispatcherServlet调用时传递的两个核心对象
     * @param @param request
     * @param @param response    设定文件
     * @return void    返回类型
     * @throws
     */
    public void init(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    /**
     *
     * @Title: put
     * @Description: 将参数放置到Request的attribute里面
     * @param @param paramName
     * @param @param paramValue    设定文件
     * @return void    返回类型
     * @throws
     */
    protected void put(String paramName,Object paramValue){
        request.setAttribute(paramName, paramValue);
    }
    /**
     *
     * @Title: get
     * @Description: 获取request对象中的attribute属性
     * @param @param paramName
     * @param @return    设定文件
     * @return Object    返回类型
     * @throws
     */
    protected Object get(String paramName){
        return request.getAttribute(paramName);
    }
    /**
     *
     * @Title: getPageData
     * @Description:获取PageData
     * @param @return    设定文件
     * @return PageData    返回类型
     * @throws
     */
    public PageData getPageData(){
        return new PageData(request);
    }
    /**
     *
     * @Title: getUUID
     * @Description:得到随机的UUID
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getUUID(){
        return UUID.randomUUID().toString();
    }
}
