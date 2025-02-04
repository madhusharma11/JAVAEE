package com.app.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.app.entities.User;

public interface UserDao {
	
//add a method for user's signin
	User signIn(String email, String password) throws SQLException;

	// add a method for getting user details(not admin) born between dates
	List<User> getUserDetails(Date begin, Date end) throws SQLException;
	// add a method for voter reg.
	
	//update password
	String changePassword(String email,String oldPwd,
			String newPwd) throws SQLException;
	//delete voter details
	String deleteVoterDetails(int voterId) throws SQLException;
//signup
	String voterRegistration(String fname, String lname, String email, String pass, String dob, String role)
			throws SQLException;
//validate
	public boolean validateEmail(String email) throws SQLException;
	
}
