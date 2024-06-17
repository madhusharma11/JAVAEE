package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.booking.DButils.DBUtils;
import com.booking.core.Doctor;

public class DoctorDaoImpl implements DoctorDao {
	private Connection connection;
	private PreparedStatement pst1;

	public DoctorDaoImpl() throws SQLException {

		connection = DBUtils.getConnection();
		pst1 = connection.prepareStatement("select * from doctors where status=false");
	}

	// Id,name, speciality,morningSlot,eveningSlot,status |
	public  List<Doctor> getDoctors() throws SQLException {
		List<Doctor> list = new ArrayList();
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				list.add(new Doctor(rst.getInt(1), rst.getString(2), rst.getString(3),
						rst.getTime(4), rst.getTime(5)));

			}
		}
		return list;

	}

}
