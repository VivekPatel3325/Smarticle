package com.asdc.smarticle.user;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.httpresponse.BaseController;
import com.asdc.smarticle.httpresponse.ResponseVO;
import com.asdc.smarticle.mailing.EmailService;
import com.asdc.smarticle.pswdencrydecry.CipherConfig;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.exception.UserExistException;

/**
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	EmailService emailServiceImpl;

	@Autowired
	UserService userService;

	@Autowired
	TokenService tokenService;

	@Autowired
	CipherConfig cipherCf;

	@Value("${enc.key}")
	private String key;

	/**
	 * Create user account with the given credentials.
	 *
	 * @param User model containing user details.
	 * @return the response entity
	 * @throws UserExistException If the user is registered with the given email id.
	 */
	@PostMapping(ApplicationUrlPath.USER_REGISTER_REQ_PATH)
	public ResponseVO<String> registerUser(@RequestBody User user) {

		try {
			userService.registerUser(user);
			Token token = tokenService.createToken(user);
			emailServiceImpl.sendConfirmationEmail(user, token);
		} catch (UserExistException e) {

			return error(HttpStatus.CONFLICT.value(), e.getMessage(), false);

		} catch (MessagingException e) {
			return error(HttpStatus.CONFLICT.value(), e.getMessage(), false);
		}

		return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
	}

	/**
	 * Activate the user account .
	 *
	 * @param token token string to be verified for user account activation.
	 * @return true if user account is activated else false.
	 */
	@PostMapping(ApplicationUrlPath.USER_ACCOUNT_ACTIVATION_REQ_PATH)
	public ResponseVO<String> activateUserAccount(@RequestParam String token) {

		boolean isAccActivated = userService.verifyUser(token);

		return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
	}

}
