package com.blogs.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogs.entities.User;

public interface UserService {
	User signInUser(String email, String pwd);
}
