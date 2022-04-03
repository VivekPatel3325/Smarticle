package com.asdc.smarticle.token;

import org.springframework.stereotype.Component;

@Component
public class TokenFactory {

	public Token getTokenInstance() {

		return new Token();
	}

}
