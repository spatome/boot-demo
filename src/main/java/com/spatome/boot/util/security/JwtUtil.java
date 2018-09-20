package com.spatome.boot.util.security;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public final static String BASE_SECRET = "jwt.demo.spatome.com";

	/*加密算法*/
	public final static SignatureAlgorithm JWT_ALG = SignatureAlgorithm.HS256;

	public static String buildJwt(String userName, Map<String, Object> extendMap, long ttlMillis){
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		JwtBuilder builder = Jwts.builder()
		.signWith(JWT_ALG, BASE_SECRET)
		.setSubject(userName)
		.setIssuedAt(now);

		if(extendMap!=null){
			for (Map.Entry<String, Object> entry : extendMap.entrySet()) {
				builder.claim(entry.getKey(), entry.getValue());
			}
		}

		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis; 
			Date exp = new Date(expMillis); 
			builder.setExpiration(exp); 
		}

		return builder.compact();
	}

	public static Claims unJwt(String token){
		return Jwts.parser().setSigningKey(BASE_SECRET).parseClaimsJws(token).getBody();
	}

	public static String getSubject(String token){
		Claims claims = unJwt(token);
		return claims.getSubject();
	}
}
