package com.asdc.smarticle.pswdencrydecry;

import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Contain static method to return instance of the SimpleStringPBEConfig.
 * 
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-02-19
 */
@Component
public class CipherConfig {

	@Value("${secretKey}")
	private String secKey;

	/**
	 * Set configuration to encrypt and decrypt password.
	 * 
	 * @return - Instance of SimpleStringPBEConfig.
	 */
	public SimpleStringPBEConfig getCipherConfig() {

		SimpleStringPBEConfig cipherConfig = new SimpleStringPBEConfig();
		System.out.println(secKey);
		cipherConfig.setPassword("smarticlesmarticle");
		cipherConfig.setAlgorithm("PBEWithHMACSHA512AndAES_256");
		cipherConfig.setKeyObtentionIterations("1000");
		cipherConfig.setPoolSize("4");
		cipherConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		cipherConfig.setIvGenerator(new RandomIvGenerator());

		return cipherConfig;
	}

}
