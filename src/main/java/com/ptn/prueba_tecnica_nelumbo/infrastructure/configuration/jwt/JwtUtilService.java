package com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {

	@Value("${jwtsecret}")
	private String secret;

	@Value("${expiration}")
	private int expiration;

	// LLAVE_MUY_SECRETA => [Base64] => TExBVkVfTVVZX1NFQ1JFVEE=
//	private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEE=";


	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(extractAllClaims(token));
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		// Agregando informacion adicional como "claim"
//		var rol = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
//		claims.put("rol", rol);
//		return createToken(claims, userDetails.getUsername());
//	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

//	public boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}

}
