package com.tcms3.model;

import jakarta.persistence.*;

@Entity
@Table (name="students")
public class Student {

	@Id
	@GeneratedValue
	private int id;
	
	@Column (name="student_name")
	private String name;
	@Column (name="student_email")
	private String email;
	
	Student () {}
	
	public Student (String name, String email) {
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
