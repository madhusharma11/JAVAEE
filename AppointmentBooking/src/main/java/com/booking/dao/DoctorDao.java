package com.booking.dao;

import java.sql.SQLException;
import java.util.List;

import com.booking.core.Doctor;

public interface DoctorDao {
	
	public  List<Doctor> getDoctors() throws SQLException ;
	

}
