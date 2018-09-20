//package com.spatome.demo.core.util.security;
//
//import java.util.Date;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang3.StringUtils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class JwtUtil2 {
//
//	public final static String BASE_SECRET = "jwt.demo.spatome.com";
//
//	/*加密算法*/
//	public final static SignatureAlgorithm JWT_ALG = SignatureAlgorithm.HS256;
//
//    /*JWT 添加至HTTP HEAD中的前缀*/
//    public final static String JWT_SEPARATOR = "Bearer ";
//
///*    public static SecretKey generateKey(SignatureAlgorithm alg, String secret) {
//    	// 将密钥生成键转换为字节数组
//    	byte[] bytes = Base64.decodeBase64(secret);
//    	// 根据指定的加密方式，生成密钥
//    	return new SecretKeySpec(bytes, alg.getJcaName());
//    }*/
//
//    public static String buildJwt(String id, String userNo, long ttlMillis){
//    	return buildJwt(JWT_ALG, Base64.decodeBase64(BASE_SECRET), userNo, null, id, "jwt", ttlMillis);
//    }
//
//    public static String buildJwt(String userNo, long ttlMillis){
//    	return buildJwt(JWT_ALG, Base64.decodeBase64(BASE_SECRET), userNo, null, null, "jwt", ttlMillis);
//    }
//
//	/**
//	 * @param alg	加密算法
//	 * @param key	加密密钥
//	 * @param subject	面向的用户
//	 * @param audience	接收方
//	 * @param id	唯一身份标识
//	 * @param issue	签发者
//	 * @param ttlMillis
//	 * @return
//	 */
//	public static String buildJwt(SignatureAlgorithm alg,
//				byte[] secret,
//				String subject,
//				String audience,
//				String id,
//				String issue,
//				long ttlMillis
//			){
//		long nowMillis = System.currentTimeMillis();
//		Date now = new Date(nowMillis);
//
//		JwtBuilder builder = Jwts.builder()
//                .signWith(alg, secret)
//                .setSubject(subject)
//                .setAudience(audience)
//                .setId(id)
//                .setIssuer(issue)
//                .setNotBefore(now)	//生效时间
//                .setIssuedAt(now);	//签发时间
//		if (ttlMillis > 0) {
//			long expMillis = nowMillis + ttlMillis; 
//			Date exp = new Date(expMillis); 
//			builder.setExpiration(exp); 
//		}
//
//		// 在JWT字符串前添加"Bearer "字符串，用于加入"Authorization"请求头
//		return JWT_SEPARATOR + builder.compact();
//	}
//
//	public static Claims unJwt(String token) {
//		String curToken = StringUtils.substringAfter(token, JWT_SEPARATOR);
//		return Jwts.parser().setSigningKey(BASE_SECRET).parseClaimsJws(curToken).getBody();
//	}
//
//	/**
//	 * @param token
//	 * @return	获取用户
//	 */
//	public static String getSubject(String token){
//		Claims claims = unJwt(token);
//		return claims.getSubject();
//	}
//
//    public static Boolean checkJwt(String token, String subject) {
//    	if(StringUtils.isBlank(subject)){
//    		return false;
//    	}
//
//    	String tokenSubject = getSubject(token);
//
//    	return subject.equals(tokenSubject);
//    }
//
//    public static void main(String[] args) {
//		String userNo = "zw";
//
//		String token = JwtUtil2.buildJwt(userNo, 0l);
//		System.out.println(token);
//
//		System.out.println(JwtUtil2.checkJwt(token, userNo));
//	}
//
//}
