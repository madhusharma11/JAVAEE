package com.apps.services;

import com.apps.dto.ApiResponse;
import com.apps.dto.AppointmentDTO;

public interface AppointmentService{
	ApiResponse bookAppointment(AppointmentDTO newAppointment);
}
