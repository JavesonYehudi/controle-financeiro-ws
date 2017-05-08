package br.com.controlefinanceiro.utils;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {
	private static final byte[] secretBytes = "secret".getBytes();
	private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);

	public static String generateToken(String login, String pass) {
		String id = UUID.randomUUID().toString().replace("-", "");
		Date now = new Date();

		String token = Jwts.builder()
				.claim("login", login)
				.claim("pass", pass)
				.setId(id)
				.setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes).compact();

		return token;
	}

	public static String verifyToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
		return claims.get("login").toString();
	}
}
