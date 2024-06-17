package com.apps.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="patients")
public class Patient extends BaseEntity{
	private String pName;
	private String address;
	@Column(length = 50,unique = true)
	private String email;
	private String mobNo;
	private String bloodGroup;
	 @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	  private List<Appointment> appointments = new ArrayList<>();
	
	 
	 public Patient() {}
	 
	 public String getpName() {
		return this.pName;
	}
	public void setpName(String pname) {
		this.pName = pname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Override
	public String toString() {
		return "Patient [Pname=" + pName + ", address=" + address + ", email=" + email + ", mobNo=" + mobNo
				+ ", bloodGroup=" + bloodGroup + ", appointments=" + appointments + "]";
	}
	public Patient(String pname, String address, String email, String mobNo, String bloodGroup)
		 {
		super();
		this.pName = pname;
		this.address = address;
		this.email = email;
		this.mobNo = mobNo;
		this.bloodGroup = bloodGroup;
		
	}

	
//	public void addPatient(Appointment appointment)
//	{
//		appointments.add(appointment);
//		((Appointment) appointments).setPatient(this);
//	}
	
	
	
}
