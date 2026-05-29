package com.rms.model;

public class Staff {

	private final String name;
	private final String email;
	private final int age;
	private final String phone;
	private final String address;

	public Staff(String name, String email, int age, String phone, String address) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}
}
