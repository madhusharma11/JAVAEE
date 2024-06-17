package com.blogs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogs.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// add a derived finder method user for sign in
	//this will also work but we have to null checking
	//User findByEmailAndPassword(String em, String pass);	
	Optional<User> findByEmailAndPassword(String em, String pass);
}
