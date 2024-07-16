package com.lexolent.carservice.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
   
private SecretKey keys = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String generateToken(Authentication auths) {
		Collection<? extends GrantedAuthority> authorites = auths.getAuthorities();
        String roles = populateAuthorities(authorites);
        
        String jwt = Jwts.builder()
        		   .setIssuedAt(new Date())
                   .setExpiration(new Date(new Date().getTime()+86400000))
                   .claim("email", auths.getName())
                   .claim("authorities", roles)
                   .signWith(keys)
                   .compact();
        return jwt;
        
          
    }
	
	public String getTokenFromJwt(String jwt)
	{
		jwt = jwt.substring(7);
		
		Claims claims=Jwts.parserBuilder().setSigningKey(keys).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claims.get("email"));
		return email;
	}
	

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> auths=new HashSet<>();
		

		for(GrantedAuthority authority:collection) {
			auths.add(authority.getAuthority());
		}
		return String.join(",",auths);
	}
}
