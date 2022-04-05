package com.asdc.smarticle.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationTokenFactory {


	public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationTokenFactory(UserDetails userDetails) {

		return new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
	}
}
