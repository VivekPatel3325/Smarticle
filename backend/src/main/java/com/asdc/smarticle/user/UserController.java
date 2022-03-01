package com.asdc.smarticle.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.smarticle.comutil.ApplicationUrlPath;
import com.asdc.smarticle.httpresponse.BaseController;
import com.asdc.smarticle.httpresponse.ResponseVO;
import com.asdc.smarticle.mailing.EmailService;
import com.asdc.smarticle.pswdencrydecry.CipherConfig;
import com.asdc.smarticle.security.JwtUtils;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.exception.UserExistException;

/**
 * @author Vivekkumar Patel, Sarthak Patel
 * @version 1.0
 * @since 2022-02-19
 */
@CrossOrigin
@RestController
@RequestMapping("/smarticleapi/user")
public class UserController extends BaseController {

	@Autowired
	EmailService emailServiceImpl;

	@Autowired
	UserService userService;

	@Autowired
	TokenService tokenService;

	@Autowired
	CipherConfig cipherCf;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;

	@Value("${enc.key}")
	private String key;

	/**
	 * Create user account with the given credentials.
	 *
	// * @param ##sser model containing user details.
	 * @return the response entity
	 * @throws UserExistException If the user is registered with the given email id.
	 */
	@PostMapping(ApplicationUrlPath.USER_REGISTER_REQ_PATH)
	public ResponseVO<String> registerUser(@RequestBody User user) {

		try {
			userService.registerUser(user);
			tokenService.createToken(user);
		} catch (UserExistException e) {

			return error(HttpStatus.CONFLICT.value(), e.getMessage(), false);
		}
//		} catch (MessagingException e) {
//			return error(HttpStatus.CONFLICT.value(), e.getMessage(), false);
//		}

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

		userService.verifyUser(token);

		return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
	}

	@PostMapping(ApplicationUrlPath.USER_LOGIN)
	public ResponseVO<Object> authenticateUser(@RequestBody User userRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPswd()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			ResponseCookie jwtCookie = jwtUtils.generateJwtTokenCookie(userDetails.getUsername());
			userService.addJwtToken(userDetails.getUsername(), jwtCookie.getValue());
			System.out.println(
					"jwtUtils - " + jwtCookie.getValue() + " - " + jwtCookie.getMaxAge() + " - " + jwtCookie.getName());
			User user = userService.getUserByUserName(userDetails.getUsername());
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("userName", user.getUserName());
			data.put("firstName", user.getFirstName());
			data.put("lastName", user.getLastName());
			data.put("jwt-token", jwtCookie.getValue());
			data.put("msg", "User Logged in sucessfully");
			return prepareSuccessResponse(data);
		} catch (Exception e) {
			return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), false);
		}
	}

	@PostMapping(ApplicationUrlPath.USER_LOGOUT)
	public ResponseVO<String> logoutUser() {
		try {
		ResponseCookie cookie = jwtUtils.getCleanJwtTokenCookie();
		userService.removeJwtToken(cookie.getValue());
		System.out.println("jwtUtils - " + cookie.getValue() + " - " + cookie.getMaxAge() + " - " + cookie.getName());
		return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
		} catch (Exception e) {
			return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), false);
		}
	}

	@PostMapping(ApplicationUrlPath.USER_FORGOT_PASSWORD)
	public ResponseVO<String> forgotPassword(@RequestBody User userRequest) {
		try {
			User user = userService.getUserByEmailID(userRequest.getEmailID());
			if (user != null) {
				ResponseCookie jwtCookie = jwtUtils.generateJwtTokenCookie(user.getUserName());
				emailServiceImpl.sendForgotPasswordEmail(user, jwtCookie);
				return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
			}else {
				return error(HttpStatus.UNPROCESSABLE_ENTITY.value(), "No registered emailID found.", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), false);
		}
	}

	@PostMapping(ApplicationUrlPath.SET_PASSWORD_PATH)
	public ResponseVO<String> resetPassword(@RequestHeader HttpHeaders http, @RequestBody User uservo) {
		try {
			
			String jwtToken = http.getFirst("jwt-token");
			if (!jwtToken.isEmpty()) {
				String userName = jwtUtils.getUserNameFromJwt(jwtToken);
				System.out.println(("userName "+ userName+"  "+uservo.getPswd()));
				User user = userService.updateUserPassword(userName, uservo.getPswd());
				if (user != null) {
					return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success(HttpStatus.OK.value(), HttpStatus.OK.name(), true);
	}
}
