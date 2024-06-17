package com.app.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.app.entities.Candidate;
import com.app.entities.User;

public interface UserDao {
// signin
	User signIn(String email, String password) throws SQLException;

	//getting user details(not admin) born between dates
	List<User> getUserDetails(Date begin, Date end) throws SQLException;

	//voter reg.
	String voterRegistration(User newVoter) throws SQLException;

	// update password
	String changePassword(String email, String oldPwd, String newPwd) throws SQLException;

	// delete voter details
	String deleteVoterDetails(int voterId) throws SQLException;

	// update voting status
	String updateVotingStatus(int voterId) throws SQLException;
	
	

}
