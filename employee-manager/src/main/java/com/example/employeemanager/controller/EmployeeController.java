package com.example.employeemanager.controller;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ── HOME: list all employees ──────────────────────────────────────
    @GetMapping("/")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }

    // ── ADD: show add form ────────────────────────────────────────────
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    // ── ADD: handle form submit ───────────────────────────────────────
    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult bindingResult,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-employee";
        }
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("successMessage",
                "Employee '" + employee.getName() + "' added successfully!");
        return "redirect:/";
    }

    // ── EDIT: show edit form pre-filled ──────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        model.addAttribute("employee", employee);
        return "edit-employee";
    }

    // ── EDIT: handle form submit ──────────────────────────────────────
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "edit-employee";
        }
        employee.setId(id);
        employeeRepository.save(employee);
        redirectAttributes.addFlashAttribute("successMessage",
                "Employee '" + employee.getName() + "' updated successfully!");
        return "redirect:/";
    }

    // ── DELETE ────────────────────────────────────────────────────────
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        String name = employee.getName();
        employeeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage",
                "Employee '" + name + "' deleted successfully!");
        return "redirect:/";
    }
}
