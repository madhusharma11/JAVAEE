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
@Table(name = "doctors")
public class Doctor extends BaseEntity {
	 @Column(nullable = false)
	private String name;
	 @Column(nullable = false)
	private String speciality;
	@Column(nullable = false)
	private boolean status=false;
	
	  @Column(nullable = true)
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL,
			fetch = FetchType.EAGER )
	private List<Appointment> appointments = new ArrayList<>();
	
	public Doctor() {}
	
	public Doctor(String name, String speciality) {
		super();
		this.name = name;
		this.speciality = speciality;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Override
	public String toString() {
		return "Doctor [name=" + name + ", speciality=" + speciality + ", status=" + status + ", appointments="
				+ appointments + "]";
	}

	

	public void addDoctor(Appointment appointment)
	{
		//add a post ref to the list of posts in category
		appointments.add(appointment);//parent ---> child
		appointment.setDoctor(this);//child --> parent		
	}
//	public void removeBlogPost(BlogPost post)
//	{
//		posts.remove(post);
//		post.setChosenCategory(null);
//	}

}
