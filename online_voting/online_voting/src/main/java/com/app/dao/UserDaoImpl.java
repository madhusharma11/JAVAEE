package com.app.dao;

import static com.app.utils.DBUtils.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Candidate;
import com.app.entities.User;

public class UserDaoImpl implements UserDao {

	private Connection cn;
	private PreparedStatement pst1, pst2, pst3, pst4, pst5,pst6;

	public UserDaoImpl() throws SQLException {
		cn = getConnection();
		//sign in
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		//get user details
		pst2 = cn.prepareStatement("select * from users where role='voter' and dob between ? and ?");
		// pst3 : voter reg
		/*
		 * id | first_name | last_name | email | password | dob | status | role
		 */
		pst3 = cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
		// pst4 --change pwd
		pst4 = cn.prepareStatement("update users set password=? where email=? and password=? and role='voter'");
		//update voting status
		pst6=cn.prepareStatement("update users set status=1 where id=?");
		System.out.println("user dao created...");
	}

	@Override
	public User signIn(String email, String password) throws SQLException {

		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
						if (rst.next()) 
				return new User(rst.getInt(1), rst.getString(2), rst.getString(3), email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}

	@Override
	public List<User> getUserDetails(Date begin, Date end) throws SQLException {
		List<User> users = new ArrayList<>();
		pst2.setDate(1, begin);
		pst2.setDate(2, end);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next())
				users.add(new User(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getString(5), rst.getDate(6), rst.getBoolean(7), rst.getString(8)));
		}
		return users;
		}

	@Override
	public String voterRegistration(User newVoter) throws SQLException {
		
		pst3.setString(1, newVoter.getFirstName());
		pst3.setString(2, newVoter.getLastName());
		pst3.setString(3, newVoter.getEmail());
		pst3.setString(4, newVoter.getPassword());
		pst3.setDate(5, newVoter.getDob());
		pst3.setBoolean(6, newVoter.isStatus());
		pst3.setString(7, newVoter.getRole());

		int rows = pst3.executeUpdate();
		if (rows == 1)
			return "Voter registered....";
		return "Voter registration failed !";
	}

	@Override
	public String changePassword(String email, String oldPwd, String newPwd) throws SQLException {

		pst4.setString(1, newPwd);
		pst4.setString(2, email);
		pst4.setString(3, oldPwd);
	
		int rows = pst4.executeUpdate();
		if (rows == 1)
			return "Password Changed !";
		return "Changing password  failed(invalid credentials or role )!!!!";
	}
	

	@Override
	public String deleteVoterDetails(int voterId) throws SQLException {
	
		return null;
	}
	
	

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
	
		pst6.setInt(1, voterId);
		int rows = pst6.executeUpdate();
		if(rows == 1)
			return "Voted Successfully !";
		return "Voting failed....";
	}

	// add clean up method to close DB resources
	public void cleanUp() throws SQLException {
		System.out.println("user dao cleaned up");
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (pst4 != null)
			pst4.close();
		if (pst6 != null)
			pst6.close();
		
	}
//
//	@Override
//	public List<Candidate> getResult() throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
