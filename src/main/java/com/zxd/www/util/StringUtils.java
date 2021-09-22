package com.zxd.www.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;
import java.util.Random;

/**
 * @author Makonike
 * @date 2021-09-12 23:51
 **/
public class StringUtils {

    final static Logger logger = LoggerFactory.getLogger(StringUtils.class);

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

    /**
     * 获取改属性名的set方法名
     * @param fieldName 属性名
     * @return set方法名
     */
    public static String changeToUpper(String fieldName){
        return "set" + fieldName.toUpperCase(Locale.ROOT).charAt(0) + fieldName.substring(1);
    }

    /**
     * 生成随机盐
     */
    public static String generateSalt(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    /**
     * Base64+MD5加密
     * @param password password
     * @param salt salt
     */
    public static String addSaltPassword(String password, String salt){
        Base64.Encoder encoder=Base64.getEncoder();
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("密码加密失败");
        }
        if (md5 != null) {
            password = encoder.encodeToString(md5.digest((salt + password).getBytes(StandardCharsets.UTF_8)));
        }
        return password;
    }

    /**
     * 校验密码是否正确
     * @param password password
     * @param truePassword truePassword
     * @param salt salt
     */
    public static boolean validPassword(String password, String truePassword, String salt){
        String password1 = addSaltPassword(password, salt);
        return password1.equals(truePassword);
    }


}
