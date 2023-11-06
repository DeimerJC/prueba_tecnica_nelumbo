package com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.security.entity.PrincipalUserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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
	
	public Integer getIdUsuarioFromToken(String token) {
		return (Integer)extractAllClaims(token).get("id");
	}
	
	public String getRolFromToken(String token) {
		return (String)extractAllClaims(token).get("role");
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(extractAllClaims(token));
	}

	public Claims extractAllClaims(String token) {
		Claims claims = Jwts.parserBuilder()
			    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())) // Configura la clave secreta para verificar la firma
			    .build()
			    .parseClaimsJws(token)
			    .getBody();

		return claims;//Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(PrincipalUserEntity userDetails) {
		Map<String, Object> claims = new HashMap<>();
		// Agregando informacion adicional como "claim"
		var authorities = userDetails.getAuthorities().stream().collect(Collectors.toList()).get(0);
		claims.put("id", userDetails.getId());
		claims.put("name", userDetails.getName());
		claims.put("dni", userDetails.getDni());
		claims.put("role", userDetails.getRole());
		claims.put("authorities", authorities);
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
//				.setHeaderParam("typ", "JWT")
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
