package com.example.studentmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @NotBlank(message = "Student name is required")
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Course is required")
    @Column(nullable = false)
    private String course;

    @NotBlank(message = "Duration is required")
    @Column(nullable = false)
    private String duration;

    // Default constructor
    public Student() {}

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
