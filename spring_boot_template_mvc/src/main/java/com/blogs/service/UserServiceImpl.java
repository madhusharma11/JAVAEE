package com.blogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogs.custum_exception.InvalidCredentialsException;
import com.blogs.entities.User;
import com.blogs.repository.UserRepository;

@Service // mandatory class level annotation to tell SC following is spring bean containgb
			// B.L.
@Transactional // for adding auto tx mngmnt support

public class UserServiceImpl implements UserService {
//dependency
	@Autowired
	private UserRepository userRepository;
	@Override
	public User signInUser(String email, String pwd) {
		//Optional<User> optional=
		java.util.Optional<User> optional=userRepository.findByEmailAndPassword(email, pwd);
		return optional.orElseThrow(()->
		new InvalidCredentialsException("invalid email or password"));
	}

}
