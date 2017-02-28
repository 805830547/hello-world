package com.sixstar.mvc.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sixstar.mvc.util.ClassScanner;

/**
 *
 * @ClassName: DispatcherServlet
 * @Description: 核心控制器
 * @date 2016年11月23日 下午9:06:53
 *
 */
//@WebServlet(urlPatterns = { "*.do" }, loadOnStartup = 0, initParams = {
//        @WebInitParam(name = "basePackage", value = "com.sixstar.controller"),
//        @WebInitParam(name = "viewPrefix", value = "/WEB-INF/views/"),
//        @WebInitParam(name = "viewAfterfix", value = ".jsp") })
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(DispatcherServlet.class);
    private String basePackage;
    private String viewPrefix;
    private String viewAfterfix;
    /**
     * 声明 两个静态私有变量 分别存储 Controller的实例 以及每个映射路径对应的Method
     */
    private Map<String, Object> controllers = new HashMap<String, Object>();
    /**
     * 被反射调用的Method对象
     */
    private Map<String, Method> methods = new HashMap<String, Method>();

    @Override
    public void init() {
        if (logger.isDebugEnabled()) {
            logger.debug("******SMVC框架启动初始化******");
        }
        ServletConfig config = this.getServletConfig();
        basePackage = config.getInitParameter("basePackage");
        viewPrefix = config.getInitParameter("viewPrefix");
        viewAfterfix = config.getInitParameter("viewAfterfix");
        if (logger.isDebugEnabled()) {
            logger.debug("读取的配置信息：basePackage：" + basePackage + ";viewPrefix：" + viewPrefix + ";viewAfterfix："
                    + viewAfterfix);
        }
        Map<String, Class<?>> cons = ClassScanner.scannerClass(basePackage);
        Iterator<String> itro = cons.keySet().iterator();
        while (itro.hasNext()) {
            String className = itro.next();
            Class<?> c = cons.get(className);
            try {
                // 将类实例化 并存在控制器集合
                controllers.put(className, c.newInstance());
                Method[] ms = c.getMethods();// 只拿public
                for (Method method : ms) {
                    // 如果不存在RequestMapping 放弃此方法继续循环
                    if (method.getAnnotation(RequestMapping.class) == null) {
                        continue;
                    }
                    RequestMapping req = c.getAnnotation(RequestMapping.class);
                    // 将方法对象放入到map中，类requestMapping+方法的RequestMapping作为key 全路径
                    methods.put(req.value() + method.getAnnotation(RequestMapping.class).value(), method);
                }
            } catch (Exception e) {
                logger.error("次奥，自定义MVC框架初始化失败！", e);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("******嘿嘿，SMVC框架启动初始化完成*****");
        }
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 如何获取到请求的地址
        String contextPath = request.getContextPath();// /smvc
        String uri = request.getRequestURI(); // /smvc/index/test.do
        // 映射路径 indexOf() 如果是一个字符串，他返回的是这个字符串第一个字符的位置
        String mappingPath = uri.substring(uri.indexOf(contextPath) + contextPath.length(), uri.indexOf(".do"));
        if (logger.isDebugEnabled()) {
            logger.debug("解析后的路径：" + mappingPath);
        }

        /**
         * 1.通过映射的路径在methods里面找到对应的Method
         * 2.再通过Method获取到他的声明类，然后从controllers里面找到对应的实例
         * 3.method.invoke(obj)去掉用的method
         */
        // 1.通过映射路径找到需要被调用的方法
        Method method = methods.get(mappingPath);
        // 2.通过方法找到对应到类全路径
        String className = method.getDeclaringClass().getName();
        Object controller = controllers.get(className);
        // 将Controller强制转换成父类引用
        BaseController baseController = (BaseController) controller;
        // 将实例初始化一遍
        baseController.init(request, response);
        try {
            // 当不存在ResponseBody注解时
            if (method.getAnnotation(ResponseBody.class) == null) {
                IdentifyView identifyView = (IdentifyView) method.invoke(controller);
                ResultType resultType = identifyView.getResultType();
                ResultContent resultContent = identifyView.getResultContent();
                switch (resultType) {
                case FORWORD:
                    if (logger.isDebugEnabled()) {
                        logger.debug("转发路径：" + viewPrefix + resultContent.getUrl() + viewAfterfix);
                    }
                    // 请求转发到新路径 主要用于视图的转发
                    request.getRequestDispatcher(viewPrefix + resultContent.getUrl() + viewAfterfix).forward(request,
                            response);
                    break;
                case REDIRECT:
                    if (logger.isDebugEnabled()) {
                        logger.debug("重定向到：" + contextPath + "/" + resultContent.getUrl());
                    }
                    // 重定向到内部资源
                    response.sendRedirect(contextPath + "/" + resultContent.getUrl());
                    break;
                case AJAX:
                    // 将JavaBean转换成Json字符串输出到客户端
                    PrintWriter out = response.getWriter();
                    out.println(resultContent.getJson());
                    out.close();
                    break;
                case FORWORDCHAIN:
                    if (logger.isDebugEnabled()) {
                        logger.debug("转发到：" + contextPath + "/" + resultContent.getUrl());
                    }
                    // 将请求发送给下一个控制器
                    request.getRequestDispatcher(contextPath + "/" + resultContent.getUrl()).forward(request, response);
                    break;
                }
            } else {
                method.invoke(controller);
            }

        } catch (IllegalAccessException e) {
            logger.error("方法调用异常，请检查控制器的方法定义是否符合要求！", e);
        } catch (IllegalArgumentException e) {
            logger.error("参数传递出现异常，请检查控制器的方法定义是否符合要求！", e);
        } catch (InvocationTargetException e) {
            logger.error("根据路径调用控制器失败！", e);
        }
        /**
         * 1.根据路径，反射调用对应的控制器方法
         * 2.封装请求对象，将参数传递到Controller
         * 3.处理完成后的返回操作
         */

    }
}
