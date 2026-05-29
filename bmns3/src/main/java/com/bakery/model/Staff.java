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
}
