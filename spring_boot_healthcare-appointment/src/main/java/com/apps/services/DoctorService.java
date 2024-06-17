package com.apps.services;

import com.apps.entities.Doctor;

public interface DoctorService{
	//ApiResponse bookAppointment(AppointmentDTO newAppointment);

	Doctor resiterDoctor(Doctor doctor);

	Doctor logindoctor(Long id);
}
