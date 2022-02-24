package com.asdc.smarticle.token;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;

import com.asdc.smarticle.user.User;

/**
 * Service implementation for token entity.
 * 
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-02-19
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	TokenRepository tokenRepository;

	@Value("${token.expirtytime}")
	private String tokenValidity;

	@Override
	public Token createToken(User user) {

		StringKeyGenerator tokenString = KeyGenerators.string();
		Token token = new Token();
		token.setUser(user);
		token.setExpiryDate(LocalDateTime.now().plusSeconds(Long.parseLong(tokenValidity)));
		token.setToken(tokenString.generateKey());
		tokenRepository.save(token);

		return token;
	}

	@Override
	public boolean isTokenExpired(Token token) {

		return token.getExpiryDate().isBefore(LocalDateTime.now());
	}

	@Override
	public void deleteToken(Token token) { 

		tokenRepository.delete(token);
	}

}
