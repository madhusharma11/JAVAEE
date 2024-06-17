package com.app.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.app.utils.DBUtils.*;

import com.app.entities.User;

public class UserDaoImpl implements UserDao {
	// state
	private Connection cn;
	private PreparedStatement pst1, pst2, pst3, pst4, pst5;

	
	public UserDaoImpl() throws SQLException {
		
		cn = getConnection();
		
		// pst1 : sign in
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		
		// pst2 : get user details
		pst2 = cn.prepareStatement("select * from users where role='voter' and dob between ? and ?");
		
		// pst3 : voter reg	
		pst3 = cn.prepareStatement("insert into users(first_name,last_name,email,password,dob,role) values(?,?,?,?,?,?)");
		
		// pst4 --change pwd
		pst4 = cn.prepareStatement("update users set password=? where email=? and password=? and role='voter'");

		//validate email
		pst5=cn.prepareStatement("select * from users where email=?");
		
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
	public String voterRegistration(String firstName, String lastName, String email, String password,
			  String dob, String role) throws SQLException {
		
		
		
		
		System.out.println("email:03"+email);
		pst3.setString(1, firstName);
		pst3.setString(2, lastName);
		pst3.setString(3,email);
		pst3.setString(4,password);
		pst3.setDate(5,Date.valueOf(dob));	
		pst3.setString(6, role);

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
		// 2. exec update : DML
		int rows = pst4.executeUpdate();
		if (rows == 1)
			return "Password Changed !";
		return "Changing password  failed(invalid credentials or role )!!!!";
	}
	
	
	@Override
	public boolean validateEmail(String email) throws SQLException
	{
		pst5.setString(1, email);
		try(ResultSet rst = pst5.executeQuery())
		{
			if(rst.next())
			{
				return false;
			}
			else return true;
		}
		
	}

	@Override
	public String deleteVoterDetails(int voterId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
	
	}

}
