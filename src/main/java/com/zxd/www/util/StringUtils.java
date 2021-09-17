package com.zxd.www.util;

import java.util.Locale;

/**
 * @author Makonike
 * @date 2021-09-12 23:51
 **/
public class StringUtils {

    /**
     * 将数据库字段名改为驼峰命名
     * @param columnName 数据库字段名
     * @return 驼峰命名
     */
    public static String changeColumnName(String columnName){
        String[] ss = columnName.split("_");
        if(ss.length ==1){
            return columnName;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ss[0]);
        for (int i = 1; i < ss.length; i++) {
            sb.append(upperFirstCase(ss[i]));
        }

        return sb.toString();

    }

    private static String upperFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }

    /**
     * 将全类名改为首字母小写的类名
     * @param className 全类名
     * @return 首字母小写的类名
     */
    public static String fullToClassName(String className){
        className = className.substring(className.lastIndexOf(".") + 1);
        return className.toLowerCase(Locale.ROOT).charAt(0) + className.substring(1);
    }


}
