package com.example.employeemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is required")
    @Column(name = "employee_id", unique = true, nullable = false)
    private String employeeId;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be a positive number")
    @Column(nullable = false)
    private Double salary;

    @NotNull(message = "Joining date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    // Default constructor
    public Employee() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", employeeId='" + employeeId + "', name='" + name +
               "', department='" + department + "', salary=" + salary +
               ", joiningDate=" + joiningDate + "}";
    }
}
