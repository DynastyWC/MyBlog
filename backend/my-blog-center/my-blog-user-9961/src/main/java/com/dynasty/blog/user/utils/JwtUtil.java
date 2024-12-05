package com.dynasty.blog.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import java.util.Date;
import java.util.Map;

public class JwtUtil {
  private static final long EXPIRE_TIME = 60 * 60 * 1000;  // 15分钟
  private static final String TOKEN_SECRET = "dynasty";  // 密钥盐

  /**
   * 签名生成
   * @param claims 用户信息
   * @return 加密的token
   */
  public static String getToken(Map<String, Object> claims) {
    Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

    // 附带username信息
    return JWT.create()
        .withExpiresAt(date)
        .withClaim("claims", claims)
        .sign(algorithm);
  }

  /**
   * 签名验证
   * @param token token
   * @return 是否验证通过
   */
  public static Map<String, Object> parseToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
      DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
      return jwt.getClaim("claims").asMap();
    } catch (Exception e){
      return null;
    }
  }
}
