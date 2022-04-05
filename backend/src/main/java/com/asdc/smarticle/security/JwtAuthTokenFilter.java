package com.asdc.smarticle.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.asdc.smarticle.security.service.UserDetailsServiceImpl;

/**
 * 
 * @author Sarthak Patel
 * @version 1.0
 * @since 2022-02-19
 */

public class JwtAuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	UsernamePasswordAuthenticationTokenFactory authenticationTokenFactory;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if (jwt != null && jwtUtils.validateJwt(jwt)) {
				String username = jwtUtils.getUserNameFromJwt(jwt);

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				System.out.println("doFilterInternal");
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
				
				UsernamePasswordAuthenticationToken authentication = authenticationTokenFactory.getUsernamePasswordAuthenticationTokenFactory(userDetails);

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		filterChain.doFilter(request, response);
	}

	public String parseJwt(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtTokenFromCookies(request);
		return jwt;
	}
}
