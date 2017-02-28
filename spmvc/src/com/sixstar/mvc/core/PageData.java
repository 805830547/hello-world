package com.sixstar.mvc.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: PageData
 * @Description: 将页面提交的参数自动进行封装,并且能够支持数据往后面的传递
 * @author Administrator
 *
 */
public class PageData extends HashMap<Object, Object> {

    private static final long serialVersionUID = 1L;
    // 存储数据
    private Map<Object, Object> map = null;

    public PageData(HttpServletRequest request) {
        // request 里面的请求对象
        Map<String, String[]> properties = request.getParameterMap();
        // 用来给外部使用的map
        map = new HashMap<Object, Object>();
        String name = "";
        String[] valueObjects = null;

        for (Entry<String, String[]> entry : properties.entrySet()) {
            name = entry.getKey();
            valueObjects = entry.getValue();
            if (null == valueObjects) {
                map.put(name, "");
            } else {
                String value = "";
                for (String valueObject : valueObjects) {
                    value += valueObject + ",";
                }
                map.put(name, value.substring(0, value.length() - 1));
            }
        }
    }

    /**
     * 空构造
     */
    public PageData() {
        map = new HashMap<Object, Object>();
    }

    @Override
    public Object get(Object key) {
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

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }
}
