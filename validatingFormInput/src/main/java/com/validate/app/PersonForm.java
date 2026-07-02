package com.validate.app;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonForm {
	@NotNull
	@Size(min = 2, max = 30)
	private String name;
	
	@NotNull
	@Min(18)
	private Integer age;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getage() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person(Name: " + name + ", Age: " + age + ")";
	}

	// public static void main(String[] args) {
	// 	// TODO Auto-generated method stub

	// }

}
