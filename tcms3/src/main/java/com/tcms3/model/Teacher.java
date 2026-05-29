package com.tcms3.model;

import jakarta.persistence.*;

@Entity
@Table (name="teachers")
public class Teacher {

	@Id
	@GeneratedValue
	private int id;
	
	@Column (name="teacher_name")
	private String name;
	@Column (name="teacher_email")
	private String email;
	
	Teacher() {}
	
	public Teacher(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
