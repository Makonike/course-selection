package com.zxd.www.util;

import com.zxd.www.constant.JwtConstant;
import com.zxd.www.po.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Makonike
 * @date 2021-09-20 20:47
 **/
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 生成jwt
     * @param user user
     * @return jwt
     */
    public static String generateJwt(User user){

        // 可不设置 默认格式是{"alg":"HS256"}
        Map<String, Object> map = new HashMap<>(2);
        map.put(JwtConstant.ALG_KEY, JwtConstant.ALG_VALUE);
        map.put(JwtConstant.TYP_KEY, JwtConstant.TYP_VALUE);

        // 载荷 map / Jwt的载荷，第二部分
        Map<String,Object> claims = new HashMap<>(3);

        // 私有声明 / 自定义数据，根据业务需要添加
        claims.put(JwtConstant.CLAIMS_ID_KEY,user.getUserId());
        claims.put(JwtConstant.CLAIMS_USERNAME_KEY, user.getUsername());

        // 自定义标准声明
        claims.put(JwtConstant.CLAIMS_ISS_KEY, JwtConstant.JWT_ISS);
            /*	iss: jwt签发者
                sub: jwt所面向的用户
                aud: 接收jwt的一方
                exp: jwt的过期时间，这个过期时间必须要大于签发时间
                nbf: 定义在什么时间之前，该jwt都是不可用的.
                iat: jwt的签发时间
                jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
            */

        // 设置jwt body
        return Jwts.builder()
                // header
                .setHeader(map)
                // claims
                .setClaims(claims)
                // jwt-id
                .setId(UUID.randomUUID().toString())
                // 签发时间
                .setIssuedAt(new Date())
                // exp time
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.ACCESS_TOKEN_EXPIRATION))
                .setSubject(JwtConstant.SUBJECT)
                // sign
                .signWith(JwtConstant.SIGNATURE_ALGORITHM, JwtConstant.PERSONAL_SECRET)
                .compact();
    }

    public static Claims parseToken(String token){
        Claims claims = null;
        try {
             claims = Jwts.parser()
                    .setSigningKey(JwtConstant.PERSONAL_SECRET)
                    // 前端返回时 请求头中key:Authorization，value:token-[TOKEN]
                    .parseClaimsJws(token.replace(JwtConstant.TOKEN_PREFIX, ""))
                    .getBody();
        } catch (ExpiredJwtException e){
            logger.info("--- token已失效，请重新登录！ ---");
        } catch (Exception e){
            logger.info("--- token解析异常，用户认证失败！ ---");
        }
        return claims;
    }

    public static boolean verifyToken(String token){
        Claims claims = parseToken(token);
        return claims != null;
    }



}
