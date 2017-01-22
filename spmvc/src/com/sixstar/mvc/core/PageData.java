package com.sixstar.mvc.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: PageData
 * @Description: 将页面提交的参数自动进行封装,并且能够支持数据往后面的传递
 * @date 2016年11月25日 下午8:53:56
 *
 */
public class PageData extends HashMap implements Map {
    // 存储数据
    private Map map = null;
    // 请求对象
    private HttpServletRequest request;

    public PageData(HttpServletRequest request) {
        this.request = request;
        // request 里面的请求对象
        Map properties = request.getParameterMap();
        // 用来给外部使用的map
        map = new HashMap();
        Iterator iterator = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";

        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            name = (String) entry.getKey();
            Object valueObject = entry.getValue();
            if (null == valueObject) {
                map.put(name, "");
            } else if (valueObject instanceof String[]) {
                String value = "";
                String[] values = (String[]) valueObject;
                for (int i = 0; i < values.length; i++) {

                    value += values[i] + ",";
                }
                map.put(name, value.substring(0, value.length() - 1));
            } else {
                map.put(name, valueObject.toString());
            }

        }
    }

    /**
     * 空构造
     */
    public PageData() {
        map = new HashMap();
    }

    @Override
    public Object get(Object key) {
        Object obj = null;
        return map.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    public String getString(Object key) {
        Object o = get(key);
        if (o == null) {
            return "";
        }
        return o.toString();
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }
}
