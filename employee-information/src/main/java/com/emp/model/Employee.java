package com.emp.model;

import java.io.*;

import jakarta.persistence.*;

@Entity
@Table (name = "employee_data")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name = "Employee_Name")
	private String employeeName;
	
	@Column (name = "DOB")
	private String DOB;
	
	@Column (name = "DOJ")
	private String DOJ;
	
	@Column (name = "Salary")
	private String salary;
	
	@Column (name = "Department")
	private String department;
	
	
	public String getEmployeeName() { return employeeName; }
	public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
	
	public String getDOB() { return DOB; }
	public void setDOB(String DOB) { this.DOB = DOB; }
	
	public String getDOJ() { return DOJ; }
	public void setDOJ(String DOJ) { this.DOJ = DOJ; }
	
	public String getSalary() { return salary; }
	public void setSalary(String salary) { this.salary = salary; }
	
	public String getDepartment() { return department; }
	public void setDepartment(String department) { this.department = department; }
}