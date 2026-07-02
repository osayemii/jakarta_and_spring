package com.bakery.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bakery_staff")
@Access(AccessType.FIELD)
public class Staff {

	@Id
	@GeneratedValue
	private int id;
	private String staffName;
	private String staffEmail;
	private int staffAge;
	private String staffPhone;
	private String staffAddress;

	protected Staff() {}

	public Staff(String staffName, String staffEmail, int staffAge, String staffPhone, String staffAddress) {
		this.staffName = staffName;
		this.staffEmail = staffEmail;
		this.staffAge = staffAge;
		this.staffPhone = staffPhone;
		this.staffAddress = staffAddress;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return staffName;
	}

	public String getEmail() {
		return staffEmail;
	}

	public int getAge() {
		return staffAge;
	}

	public String getPhone() {
		return staffPhone;
	}

	public String getAddress() {
		return staffAddress;
	}

	public void setName(String staffName) {
		this.staffName = staffName;
	}

	public void setEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public void setAge(int staffAge) {
		this.staffAge = staffAge;
	}

	public void setPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	public void setAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}
}
