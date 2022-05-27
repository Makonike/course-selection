package com.zxd.www.test;

import com.zxd.www.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import java.text.SimpleDateFormat;

/**
 * @author Makonike
 * @date 2021-09-21 15:04
 **/
public class TokenTest {


    public static void main(String[] args) {
        Claims claims = null;
        try {
            claims = JwtUtils.parseToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyIiwiaXNzIjoienhkIiwiaWQiOjEsInVzZXJOYW1lIjoiMzEyMDAwNTE4MCIsImV4cCI6MTYzMjIwODM3NSwiaWF0IjoxNjMyMjA4Mzc0LCJqdGkiOiIzYTEzNTRiZS01YjU0LTQxYmQtODJkNy0yOGRmOGQ3ZDAwZWQifQ.G8VhE1wqpE_SJ-GbRdUJHDdR2TkIFOaEvHDPupjaHkI");
            System.out.println("用户id=" + claims.get("id"));
            System.out.println("用户名=" + claims.get("userName"));
            System.out.println("登陆时间" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
            System.out.println("过期时间" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(claims.getExpiration()));
            System.out.println("用户角色" + claims.get("role"));
        } catch (ExpiredJwtException e){
            e.printStackTrace();
            System.out.println(claims);
            System.out.println("token失效，请重新登录");
        }

    }
}
