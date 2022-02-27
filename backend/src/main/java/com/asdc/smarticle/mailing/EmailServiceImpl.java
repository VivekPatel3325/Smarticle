package com.asdc.smarticle.mailing;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.user.User;

/**
 * Implementation to for email entity.
 * 
 * @author Vivekkumar Patel, Sarthak Patel
 * @version 1.0
 * @since 2022-02-19
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	TemplateEngine templateEngine;

	@Value("${client.url}")
	private String clientURl;

	@Override
	public void sendConfirmationEmail(User user, Token token) throws MessagingException {
		Context context = new Context();

		context.setVariable("verificationLink", httpVeificationURL(token));

		String template = templateEngine.process("welcome", context);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setFrom("patelvivek221996@gmail.com");
		mimeMessageHelper.setTo(user.getEmailID());
		mimeMessageHelper.setText(template, true);
		mimeMessageHelper.setSubject("Smarticle user account verification");
		javaMailSender.send(mimeMessage);
	}

	public String httpVeificationURL(Token token) {
		final String verificationURl = UriComponentsBuilder.fromHttpUrl(clientURl)
				.path("/verify").queryParam("token", token.getToken())
				.toUriString();
		return verificationURl;
	}

	public String httpResetPasswordnURL(ResponseCookie token) {
		final String verificationURl = UriComponentsBuilder.fromHttpUrl(clientURl)
				.path("/reset").queryParam("token", token.getValue()).toUriString();
		return verificationURl;
	}

	@Override
	public void sendForgotPasswordEmail(User user, ResponseCookie token) throws MessagingException {
		Context context = new Context();
		context.setVariable("resetPasswordLink", httpResetPasswordnURL(token));
		String template = templateEngine.process("resetPassword", context);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setTo(user.getEmailID());
		mimeMessageHelper.setText(template, true);
		mimeMessageHelper.setSubject("Reset Password");
		javaMailSender.send(mimeMessage);
	}

}
