package com.zxd.www.global.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-21 18:37
 **/
public class FilterExcludeUrl {

    public static List<String> pattenURL = new ArrayList<>();

    static {
        pattenURL.add("/user/login");
        pattenURL.add("/user/register");
        pattenURL.add("/");
    }
}
