package com.validation.test.model;

import com.validation.test.validation.Students;

public class Student {
     private String name;
     private int age;
     @Students
     private String email;
     
     public Student() {
     }
     
     Student(String name, int age, String email) {
    	 this.name = name;
    	 this.age = age;
    	 this.email = email;
     }
     
     public void setName(String name) { this.name = name; }
     public String getName() { return name; }
     
     public void setAge(int age) { this.age = age; }
     public int getAge() { return age; }
     
     public void setEmail(String email) { this.email = email; }
     public String getEmail() { return email; }
     
}
