package com.bank.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * JPA entity mapped to the {@code Customer_Details} table.
 */
@Entity
@Table(name = "Customer_Details")
public class Bank implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Customer_ID")
	private String customerId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Age")
	private Integer age;

	@Column(name = "Date_of_Birth")
	private String dateOfBirth;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
