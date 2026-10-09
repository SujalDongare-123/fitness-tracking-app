package com.project.fitness.security;


import java.security.Key;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils
{
	@Value("${JWT_SECRET}")
	private String jwtSecret;
	private int jwtExpirationMs=172800000;
	
	public String getJwtFromHeader(HttpServletRequest request)
	{
		String bearerToken=request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer "))
		return bearerToken.substring(7);
		
		return null;
		
	}
	
	public String generateToken(String userId , String role)
	{
			return  Jwts.builder()
				.subject(userId)
				.claim("roles",List.of((role)))
				.issuedAt(new Date())
				.expiration(new Date(new Date().getTime()+jwtExpirationMs))
				.signWith(key())
				.compact();
	}
	
	public boolean validatejwtToken(String jwtToken)
	{
		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwtToken);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	private Key key()
	{
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret) );
	}
	
	
	public String getUserIdFromToken(String jwt)
	{
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload().getSubject();
	}
	
	
	public Claims getAllClaims(String jwt)
	{
		return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload();
	}
	
	
}
