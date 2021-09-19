package com.zxd.www.util.mvc.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图结果数据
 * @author Makonike
 * @date 2021-09-18 14:49
 **/
public class ModelAndView {

    /**
     * 返回路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> attribute;

    public ModelAndView(String path){
        this.path = path;
        attribute = new HashMap<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public ModelAndView setAttribute(String key, Object obj) {
        attribute.put(key, obj);
        return this;
    }
}
