package com.asdc.smarticle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.asdc.smarticle.pswdencrydecry.CipherConfig;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.token.TokenRepository;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import com.asdc.smarticle.user.UserService;
import com.asdc.smarticle.user.UserServiceImpl;
import com.asdc.smarticle.user.exception.UserExistException;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceUnitTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	private UserServiceImpl userService;// system under test

	@Mock
	private UserRepository userRepo;// Dependency is mocked.

	@Mock
	private TokenRepository tokenRepository;

	@Mock
	private TokenService tokenService;

	@MockBean
	private User user;

	@MockBean
	private Token token;

	@Test
	void testIsEmailIdRegistered() {

		String emailIDString = "patelvivek221996@gmail.com";

		Optional<User> userEntity = Optional.of(user);

		Mockito.when(userRepo.findByEmailID(emailIDString)).thenReturn(userEntity);

		Assert.assertTrue(userService.isEmailIdRegistered(emailIDString));

		Mockito.when(userRepo.findByEmailID(emailIDString)).thenReturn(Optional.empty());

		Assert.assertFalse(userService.isEmailIdRegistered(emailIDString));
	}

	@Test
	void testIsUsernameRegistered() {

		String userName = "vkpatel4312";

		Optional<User> userEntity = Optional.of(user);

		Mockito.when(userRepo.findByUserName(userName)).thenReturn(userEntity);

		Assert.assertTrue(userService.isUsernameRegistered(userName));

		Mockito.when(userRepo.findByUserName(userName)).thenReturn(Optional.empty());

		Assert.assertFalse(userService.isUsernameRegistered(userName));
	}

	@Test
	void testVerifyUserInvalidData() {
		Long id = (long) 1;

		// Test null token string
		String tokenString = null;
		Assert.assertFalse(userService.verifyUser(tokenString));

		// Test empty token string
		tokenString = "";
		Assert.assertFalse(userService.verifyUser(tokenString));

		// Test token which does not exist
		Mockito.when(tokenRepository.findByToken(tokenString)).thenReturn(null);
		Assert.assertFalse(userService.verifyUser(tokenString));

		// Test expired token
		Mockito.when(tokenService.isTokenExpired(token)).thenReturn(true);
		Assert.assertFalse(userService.verifyUser(tokenString));

		// Test token for which user does not exist
		Mockito.when(userRepo.findById(id)).thenReturn(Optional.empty());
		Assert.assertFalse(userService.verifyUser(tokenString));

		Optional<User> userEntity = Optional.of(user);

	}

	@Test
	void testVerifyUserValidData() {

		Long id = (long) 1;
		Optional<User> userEntity = Optional.of(user);

		Token tokenDetailToken = new Token();
		User userDetail = new User();
		Optional<User> userDetailOptional = Optional.of(userDetail);

		String tokenString = "1b132cda7a92ad0f";
		Mockito.when(tokenRepository.findByToken(tokenString)).thenReturn(tokenDetailToken);
		Mockito.when(tokenService.isTokenExpired(token)).thenReturn(false);
		Mockito.when(userRepo.findById(id)).thenReturn(userDetailOptional);

		Mockito.when(token.getUser()).thenReturn(userDetail);
		Mockito.when(user.getId()).thenReturn(id);
		/*
		 * // Mockito.when(userEntity.get()).thenReturn(userDetailUser);
		 * Assert.assertTrue(userService.verifyUser(tokenString));
		 * 
		 * Mockito.verify(user).setVerified(true);
		 * 
		 * // Mockito.verify(userRepo).save(userDetail);
		 * Mockito.verify(tokenService).deleteToken(tokenDetailToken);
		 */
	}

}
