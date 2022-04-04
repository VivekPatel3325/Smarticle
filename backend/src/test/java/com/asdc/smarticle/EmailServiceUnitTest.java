package com.asdc.smarticle;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.asdc.smarticle.mailing.EmailServiceImpl;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceUnitTest {

	@Test
	void contextLoads() {
	}
	
	@InjectMocks
	private EmailServiceImpl emailService;
	
	@Mock
	TemplateEngine templateEngine;
	
	@Mock
	JavaMailSender javaMailSender;
	
	@Mock
	MimeMessage mimeMessage;
	
	@Mock
	MimeMessageHelper mimeMessageHelper;
	
	@Mock
	UriComponentsBuilder uriComponentsBuilder;
	
	@MockBean
	Token token;
	
	@MockBean
	User user;
	
	@Test
	void testSendConfirmationEmail() throws MessagingException {
		
		Context context = new Context();
		
//		String templateString = "template";
//		Mockito.when(emailService.httpVeificationURL(token)).thenReturn("http://test");
//		Mockito.when(UriComponentsBuilder.fromHttpUrl("http://test.com").path("test")).thenReturn(uriComponentsBuilder);
//		Mockito.when(token.getToken()).thenReturn("token");
//		Mockito.when(uriComponentsBuilder.queryParam("token", token.getToken()));
//		Assert.assertEquals("http://test/token", emailService.httpVeificationURL(token));
//		
//		context.setVariable("verificationLink", emailService.httpVeificationURL(token));
//		Mockito.when(templateEngine.process("welcome", context)).thenReturn(templateString);
//		Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
//		mimeMessageHelper = new MimeMessageHelper(mimeMessage);
//		mimeMessageHelper.setFrom("testFrom");
//		mimeMessageHelper.setTo("testTo");
//		mimeMessageHelper.setText(templateString,true);
//		mimeMessageHelper.setSubject("subject");
//		javaMailSender.send(mimeMessage);
//		emailService.sendConfirmationEmail(user, token);
	}
}
