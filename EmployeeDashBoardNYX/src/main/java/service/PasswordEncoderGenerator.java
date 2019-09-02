package service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
        <dependency>
    		<groupId>org.springframework.security</groupId>
    		<artifactId>spring-security-web</artifactId>
		</dependency>
 */

public class PasswordEncoderGenerator {

	public String hashPassword(String password) {
		String encodedPassword;
		BCryptPasswordEncoder PE = new BCryptPasswordEncoder();
		encodedPassword = PE.encode(password);
		return encodedPassword;
		// generates a hashed version of the password by using BCryptPasswordEncoder
		// will randomly generate salt to get a string of length 60
		// $2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.
		

	}
}
