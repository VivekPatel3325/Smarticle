package com.asdc.smarticle;


import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.articletag.TagFactory;
import com.asdc.smarticle.articletag.TagRepository;
import com.asdc.smarticle.articletag.TagService;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.token.TokenFactory;
import com.asdc.smarticle.token.TokenRepository;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.KeyGenerator;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TokenServiceUnitTest {
	

	 

	@MockBean
	private TokenRepository tokenRepository;
	
	@Autowired
	TokenService tokenService;
	
	@MockBean
	TokenFactory tokenFactory;
	
	@MockBean
	private Token token;
	
	@MockBean
	private StringKeyGenerator stringKeyGenerator;
	
	@MockBean
	private User user;

	
	@Test
	void testTokenExpired() {
		
		Mockito.when(token.getExpiryDate()).thenReturn(LocalDateTime.now().minusSeconds(70));
		Assert.assertTrue(tokenService.isTokenExpired(token));
		
		
	}
	
	@Test
	void testDeleteToken() {
		
		tokenService.deleteToken(token);
		Mockito.verify(tokenRepository, Mockito.times(1)).delete(token);
		
		
	}
	
	 
 	 
	@Test 
	void testCreateToken() {

		
		Mockito.when(tokenFactory.getTokenInstance()).thenReturn(token);
		token.setUser(user);
		token.setExpiryDate(LocalDateTime.now().plusSeconds(Long.parseLong("10000000")));
		token.setToken(stringKeyGenerator.generateKey());
		Mockito.when(tokenRepository.save(token)).thenReturn(token);
		Assert.assertEquals(token, tokenService.createToken(user));
	}
	 
	 

}
