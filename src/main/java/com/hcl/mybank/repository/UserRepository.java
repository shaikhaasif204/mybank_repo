package com.hcl.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcl.mybank.model.User; 

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.username = :username and u.password = :password")
	public User login(@Param("username") String username,@Param("password") String password);
	
	@Query("select u from User u where u.id <> :id")
	public List<User> findPayees(@Param("id") long id);
	
	
}
