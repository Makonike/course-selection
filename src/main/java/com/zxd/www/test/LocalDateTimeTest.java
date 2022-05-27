package com.zxd.www.test;

import java.time.LocalDateTime;

/**
 * @author Makonike
 * @date 2021-09-26 10:28
 **/
public class LocalDateTimeTest {


    public static void main(String[] args) {
        //获取当前时间
        LocalDateTime nowTime= LocalDateTime.now();
        //自定义时间
        LocalDateTime endTime = LocalDateTime.of(2017, 10, 22, 10, 10, 10);
        //已过期
        System.out.println(endTime.compareTo(nowTime));
    }
}
