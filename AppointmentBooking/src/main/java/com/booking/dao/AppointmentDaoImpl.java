package com.booking.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.booking.DButils.DBUtils;

public class AppointmentDaoImpl implements AppointmentDao{
	private Connection cn;
	public PreparedStatement pst1;
	
	
	public AppointmentDaoImpl() 
	{
		cn=DBUtils.getConnection();
		try {
			pst1=cn.prepareStatement("insert into appointment(doctorId,date,time,patientName,address,bloodgroup) values(?,?,?,?,?,?)");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	

	

}
