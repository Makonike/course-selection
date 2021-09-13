package com.zxd.www.util;

/**
 * @author Makonike
 * @date 2021-09-12 23:51
 **/
public class StringUtils {


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


}
