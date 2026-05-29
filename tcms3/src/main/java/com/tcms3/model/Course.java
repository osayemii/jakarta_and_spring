package com.tcms3.model;

import jakarta.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue
	private int id;
	
	@Column (name = "course_name")
	private String name;
	
	@Column (name = "department")
	private String department;
	
	public Course () { }
	
	public Course (String name, String department) {
		this.name = name;
		this.department = department;
	}
	
	public int getId() { return id;}
	public String getName() { return name; }
	public String getDepartment() { return department; }
}
