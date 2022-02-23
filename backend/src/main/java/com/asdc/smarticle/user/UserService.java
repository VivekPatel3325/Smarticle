package com.asdc.smarticle.user;

import com.asdc.smarticle.user.exception.UserExistException;

/**
 * Services for user entity.
 * 
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-02-19
 */
public interface UserService {

	boolean isEmailIdRegistered(String email);
	
	boolean isUsernameRegistered(String userName);
	
	User registerUser(User user) throws UserExistException;
	
	String encodePswd(String pswd);
	
	boolean verifyUser(String token);
}
