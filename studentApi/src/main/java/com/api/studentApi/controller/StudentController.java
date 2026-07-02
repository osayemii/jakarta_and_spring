package com.api.studentApi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.studentApi.model.Student;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {
    
    private List<Student> students = new ArrayList<>();

    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}
