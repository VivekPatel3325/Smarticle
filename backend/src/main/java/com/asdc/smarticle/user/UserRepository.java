package com.asdc.smarticle.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository of user entity .
 * 
 * @author Vivekkumar Patel
 * @version 1.0
 * @since 2022-02-19
 */
@Repository
public interface UserRepository extends JpaRepository <User,Long>{

	
	 Optional<User> findByEmailID(String emailID);
	 Optional<User> findByUserName(String userName);
}
