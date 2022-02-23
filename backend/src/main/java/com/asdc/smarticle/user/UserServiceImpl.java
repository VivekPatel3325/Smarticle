package com.asdc.smarticle.user;

import java.util.Optional;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asdc.smarticle.comutil.ApiError;
import com.asdc.smarticle.pswdencrydecry.CipherConfig;
import com.asdc.smarticle.token.Token;
import com.asdc.smarticle.token.TokenRepository;
import com.asdc.smarticle.token.TokenService;
import com.asdc.smarticle.user.exception.UserExistException;


/**
 * Services for user entity.
 * 
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-02-19
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CipherConfig cipherCf;

	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	TokenService tokenService;
	
	@Override
	public boolean isEmailIdRegistered(String email) {

		boolean userExist = false;

		Optional<User> user = userRepository.findByEmailID(email);

		if (user.isPresent()) {
			userExist = true;
		}

		return userExist;
	}

	@Override
	public boolean isUsernameRegistered(String userName) {

		boolean userNameTaken = false;

		Optional<User> user = userRepository.findByUserName(userName);
		if (user.isPresent()) {
			userNameTaken = true;
		}

		return userNameTaken;
	} 

	@Override
	public User registerUser(User user) throws UserExistException {

		if (isEmailIdRegistered(user.getEmailID())) {
			throw new UserExistException(ApiError.EMAILID_ALREADY_REGISTERED);
		}

		if (isUsernameRegistered(user.getUserName())) {
			throw new UserExistException(ApiError.USERNAME_NOT_AVAILABLE);
		}

		String encPswd = encodePswd(user.getPswd());
		user.setPswd(encPswd);

		return userRepository.save(user);

	}

	@Override
	public String encodePswd(String pswd) {

		SimpleStringPBEConfig config = cipherCf.getCipherConfig();
		PooledPBEStringEncryptor cipher = new PooledPBEStringEncryptor();
		cipher.setConfig(config);

		return cipher.encrypt(pswd);

	}

	@Override
	public boolean verifyUser(String token) {

		boolean isAccountActivated = true;

		if (token == null || token.equals("")) {
			isAccountActivated = false;
		}

		Token tokenDetail = tokenRepository.findByToken(token);
		if (tokenDetail == null || tokenService.isTokenExpired(tokenDetail)) {
			isAccountActivated = false;
		} else {

			Optional<User> user = userRepository.findById(tokenDetail.getUser().getId());

			if (user.isPresent()) {
				user.get().setVerified(true);
				userRepository.save(user.get());
				tokenService.deleteToken(tokenDetail);
			} else {
				isAccountActivated = false;
			}
		}

		return isAccountActivated;
	} 
	 
	
	

}
