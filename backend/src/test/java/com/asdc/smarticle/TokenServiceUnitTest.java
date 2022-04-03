package com.asdc.smarticle;


import com.asdc.smarticle.articletag.Tag;
import com.asdc.smarticle.articletag.TagFactory;
import com.asdc.smarticle.articletag.TagRepository;
import com.asdc.smarticle.articletag.TagService;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.token.TokenRepository;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.User;
import com.asdc.smarticle.user.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceUnitTest {
	@Test
	void contextLoads() {
	}


	@MockBean
	private TokenRepository tokenRepository;
	
	@Autowired
	TokenService tagService;
	
	@MockBean
	private Token user;

	
	

}
