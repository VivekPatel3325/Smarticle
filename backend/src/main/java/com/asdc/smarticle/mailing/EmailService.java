package com.asdc.smarticle.mailing;

import javax.mail.MessagingException;

import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.user.User;

/**
* Service for email entity 
* @author  Vivekkumar Patel
* @version 1.0
* @since   2022-02-19
*/
public interface EmailService {

	void sendConfirmationEmail(User user,Token token) throws MessagingException;
}
