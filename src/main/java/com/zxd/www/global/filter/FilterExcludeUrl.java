package com.zxd.www.global.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Makonike
 * @date 2021-09-21 18:37
 **/
public class FilterExcludeUrl {

    public static List<String> pattenURL = new ArrayList<>();

    public static List<String> userAllowUrl = new ArrayList<>();

    static {
        pattenURL.add("/user/login");
        pattenURL.add("/user/register");
        pattenURL.add("/admin/login");
        pattenURL.add("/");
        userAllowUrl.add("/student/add");
        userAllowUrl.add("/student/update");
        userAllowUrl.add("/student/info");
        userAllowUrl.add("/student/select");
        userAllowUrl.add("/student/cancel");
        userAllowUrl.add("/student/course");

        userAllowUrl.add("/course/del/getAll");
        userAllowUrl.add("/course/name");
        userAllowUrl.add("/course/check");

        userAllowUrl.add("/class/ins/all");
        userAllowUrl.add("/class/gra/all");
        userAllowUrl.add("/class/cls/all");
        userAllowUrl.add("/class/cls/id");
    }
}
