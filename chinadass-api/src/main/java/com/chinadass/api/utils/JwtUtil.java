package com.chinadass.api.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
public class JwtUtil {
    public static final String SECRET = "EYJ0EXAIOIJKQILCJHBGCIOIJIUZI1NI";
    /**
     * 根据用户名新建token
     */
    public static String createToken(String userName) {
        Date expiresDate = DateUtils.addDays(new Date(), 7);//过期时间七天
        //Date expiresDate =DateUtils.addMinutes(new Date(),2); //2分钟过期用来测试
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer("step")
                .withExpiresAt(expiresDate)
                .withClaim("userName", userName)
                .sign(algorithm);
    }

    /**
     * 获取用户名
     */
    public static String  getUserName(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("step")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        Claim claim = jwt.getClaim("userName");
        return claim.asString();
    }


    public static void main(String[] args) throws Exception {
        String token = createToken("admin");
        System.out.println(token);
        String userName = getUserName(token);
        System.out.println(userName);
        //验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("EYJ0EXAIOIJKQILCJHBGCIOIJIUZI1NI")).build();
        jwtVerifier.verify(token);
    }
}
