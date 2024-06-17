package com.apps.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.apps.entities.BaseEntity;

public class AppointmentDTO extends BaseEntity {
	private Long doctor_id;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private String email;
	private String pName;
	
	public AppointmentDTO() {}
	
	public AppointmentDTO(Long doctor_id, LocalTime appointmentTime, LocalDate appointmentDate, String email, String pName) {
		super();
		this.doctor_id = doctor_id;
		this.appointmentTime = appointmentTime;
		this.appointmentDate = appointmentDate;
		this.email = email;
		this.pName = pName;
	}
	public Long getDoctor_id() {
		return doctor_id;
	}
	
	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	@Override
	public String toString() {
		return "AppointmentDTO [doctor_id=" + doctor_id + ", appointmentTime=" + appointmentTime + ", appointmentDate="
				+ appointmentDate + ", email=" + email + ", pName=" + pName + "]";
	}

	

	
	
	
	
	
}
