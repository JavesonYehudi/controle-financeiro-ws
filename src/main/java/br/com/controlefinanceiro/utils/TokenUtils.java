package br.com.controlefinanceiro.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import br.com.controlefinanceiro.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {
	private static final byte[] secretBytes = "secret".getBytes();
	private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public static String generateToken(User user) {
		String token = Jwts.builder()
				.claim("login", user.getLogin())
				.claim("pass", user.getPassword())
				.setId(user.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();
		return token;
	}

	public static String verifyToken(String token) {
		try{
			Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
			return claims.get("login").toString();
		} catch (ExpiredJwtException | IllegalArgumentException e) {
			throw e;
		}
	}
}
