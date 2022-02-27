package com.asdc.smarticle.security;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 
 * @author Sarthak Patel
 * @version 1.0
 * @since 2022-02-22
 */

@Component
public class JwtUtils {

	  @Value("${app.jwtSecret}")
	  private String jwtSecret;

	  @Value("${app.jwtExpiry}")
	  private int jwtExpiry;

	  @Value("${app.jwtCookieName}")
	  private String jwtCookie;

	  public String getJwtTokenFromCookies(HttpServletRequest request) {
	    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
	    if (cookie != null) {
	      return cookie.getValue();
	    } else {
	      return null;
	    }
	  }

	  public ResponseCookie generateJwtTokenCookie(String userName) {
	    String jwt = generateJwtTokenFromUsername(userName);
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/smarticleapi").maxAge(24 * 60 * 60).httpOnly(true).build();
	    return cookie;
	  }

	  public ResponseCookie getCleanJwtTokenCookie() {
	    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/smarticleapi").build();
	    return cookie;
	  }

	  public String getUserNameFromJwt(String token) {
	    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	  }

	  public boolean validateJwt(String authToken) {
	    try {
	      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	      return true;
	    } catch (MalformedJwtException e) {
	    	System.out.println("Invalid JWT token: "+ e.getMessage());
	    } catch (ExpiredJwtException e) {
	    	System.out.println("JWT token is expired: "+ e.getMessage());
	    } catch (UnsupportedJwtException e) {
	    	System.out.println("JWT token is unsupported: "+ e.getMessage());
	    } catch (IllegalArgumentException e) {
	    	System.out.println("JWT claims string is empty: "+ e.getMessage());
	    }
	    return false;
	  }
	  
	  public String generateJwtTokenFromUsername(String username) {   
	    return Jwts.builder()
	        .setSubject(username)
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpiry))
	        .signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	  }
}
