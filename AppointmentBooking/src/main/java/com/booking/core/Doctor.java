package com.booking.core;

import java.sql.Time;
import java.time.LocalTime;

public class Doctor {
	 private int id;
	    private String name;
	    private String speciality;
	    private Time morningSlot;
	    private Time eveningSlot;
	    private boolean status;
	    
	    
		public Doctor( int id,String name, String speciality, Time time, Time time2) {
			super();
			this.id=id;
			this.name = name;
			this.speciality = speciality;
			this.morningSlot = time;
			this.eveningSlot = time2;
			
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public Time getMorningSlot() {
			return morningSlot;
		}
		public void setMorningSlot(Time morningSlot) {
			this.morningSlot = morningSlot;
		}
		public Time getEveningSlot() {
			return eveningSlot;
		}
		public void setEveningSlot(Time eveningSlot) {
			this.eveningSlot = eveningSlot;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}

	    
}
