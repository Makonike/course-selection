package com.zxd.www.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Makonike
 * @date 2021-09-23 16:44
 **/
public class JsonUtils {

    public static <T> String toJson(T obj) {
        return (String) JSONObject.toJSON(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

}
