package com.example.studentmanager.controller;

import com.example.studentmanager.model.Student;
import com.example.studentmanager.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // ── Login page ────────────────────────────────────────────────────
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ── LIST all students (home after login) ──────────────────────────
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }

    // ── VIEW single student ───────────────────────────────────────────
    @GetMapping("/students/view/{id}")
    public String viewStudent(@PathVariable String id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "view-student";
    }

    // ── ADD: show form ────────────────────────────────────────────────
    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // ── ADD: handle submit ────────────────────────────────────────────
    @PostMapping("/students/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-student";
        }
        studentRepository.save(student);
        redirectAttributes.addFlashAttribute("successMessage",
                "Student '" + student.getStudentName() + "' added successfully!");
        return "redirect:/students";
    }

    // ── EDIT: show form pre-filled ────────────────────────────────────
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "edit-student";
    }

    // ── EDIT: handle submit ───────────────────────────────────────────
    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable String id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "edit-student";
        }
        student.setStudentId(id);
        studentRepository.save(student);
        redirectAttributes.addFlashAttribute("successMessage",
                "Student '" + student.getStudentName() + "' updated successfully!");
        return "redirect:/students";
    }

    // ── DELETE ────────────────────────────────────────────────────────
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        String name = student.getStudentName();
        studentRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Student '" + name + "' deleted successfully!");
        return "redirect:/students";
    }
}
