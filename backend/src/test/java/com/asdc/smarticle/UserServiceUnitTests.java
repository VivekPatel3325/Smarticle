package com.asdc.smarticle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.ListModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.asdc.smarticle.article.Article;
import com.asdc.smarticle.article.ArticleRepository;
import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.articletag.TagRepository;
import com.asdc.smarticle.pswdencrydecry.CipherConfig;
import com.asdc.smarticle.pswdencrydecry.CipherConfigFactory;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.token.TokenRepository;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.PooledPBEStringFactory;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import com.asdc.smarticle.user.UserService;
import com.asdc.smarticle.user.UserServiceImpl;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceUnitTest {

	/*
	 * @InjectMocks private UserServiceImpl userService;// system under test
	 */

	@Autowired
	private UserService userService;// system under test

	@MockBean
	private UserRepository userRepo;// Dependency is mocked.

	@MockBean
	private TokenRepository tokenRepository;
	
	@MockBean
	private ArticleRepository articleRepository;
	
	@MockBean
	TagRepository tagRepository;

	@MockBean
	private TokenService tokenService;

	@MockBean
	private User user;
	
	@MockBean
	PooledPBEStringFactory pooledPBEStringFactory;

	@MockBean
	private CipherConfigFactory cipherConfigFactory;
	
	@MockBean
	private SimpleStringPBEConfig simpleStringPBEConfig;
	
	@MockBean
	PooledPBEStringEncryptor pooledPBEStringEncryptor;
	
	@Autowired
	CipherConfig cipherConfig;
	
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

		User userEntity = user;

		Mockito.when(userRepo.findByUserName(userName)).thenReturn(userEntity);

		Assert.assertTrue(userService.isUsernameRegistered(userName));

		Mockito.when(userRepo.findByUserName(userName)).thenReturn(null);

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

		Optional.of(user);

	}

	@Test
	void testVerifyUserValidData() {

		Long id = (long) 1;
		Optional.of(user); 

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

	
	    
	@Test
	void testCipherConfig() {

		Mockito.when(cipherConfigFactory.getCipherConfigInstance()).thenReturn(simpleStringPBEConfig);
		simpleStringPBEConfig.setPassword("smarticlesmarticle");
		simpleStringPBEConfig.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		simpleStringPBEConfig.setKeyObtentionIterations("1000");
		simpleStringPBEConfig.setPoolSize("4");
		simpleStringPBEConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		simpleStringPBEConfig.setIvGenerator(new RandomIvGenerator());
		Assert.assertEquals(simpleStringPBEConfig, cipherConfig.getCipherConfig());

	}
	
	@Test
	void testEncodePaswd() {

		Mockito.when(cipherConfigFactory.getCipherConfigInstance()).thenReturn(simpleStringPBEConfig);
		Mockito.when(pooledPBEStringFactory.getPBEStrinInstance()).thenReturn(pooledPBEStringEncryptor);
		pooledPBEStringEncryptor.setConfig(simpleStringPBEConfig);
		Mockito.when(pooledPBEStringEncryptor.encrypt("123456")).thenReturn("abcdefg");
		Assert.assertEquals("abcdefg", userService.encodePswd("123456"));
	}
	
	
	/*This test cases test a method of user service to retrieve all posted
	 * articles 
	 */
	@Test
	void TestPostedArticle() {
		
		List<Article> articleList = new ArrayList();
		List<Map<String, String>> userDetails = new ArrayList<>();
		Mockito.when(articleRepository.findAll()).thenReturn(articleList);
		Assert.assertEquals(userDetails, userService.getUsersPostedArticle());
		
		User user = new User();
		user.setFirstName("vivek");
		user.setLastName("patel");
		user.setUserName("vkpatel4312");
		Article article = new Article();
		article.setUserId(user);
		articleList.add(article);
		Mockito.when(articleRepository.findAll()).thenReturn(articleList);
		Map<String, String> details = new HashMap<>();
		details.put("firstName", article.getUserId().getFirstName());
		details.put("lastName", article.getUserId().getLastName());
		details.put("userName", article.getUserId().getUserName());
		userDetails.add(details);

		Assert.assertEquals(userDetails, userService.getUsersPostedArticle());

	}  
	
	@Test
	void testSavePrefTag() {
		Tag tag=new Tag();
		tag.setId((long)1);
		tag.setTagName("BLOCKCHAIN");
		
		List<Tag> tagList=new ArrayList();
		tagList.add(tag);
		
		Set<Tag> tagSet=new HashSet();
		tagSet.add(tag);
		List<Long> ids = new ArrayList<>();
		for (Tag set : tagSet) {
			ids.add(tag.getId()); 
		}
		
		Mockito.when(tagRepository.findByIdIn(ids)).thenReturn(tagList);
		Mockito.when(userRepo.findByUserName("vivek")).thenReturn(user);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		Assert.assertEquals(user, userService.saveUserPrefTags("vivek", tagSet));
		
		user.setTags(tagSet);
		
		
	}
	
	
}
