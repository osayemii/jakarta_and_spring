package com.validation.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.validation.test.model.*;

import jakarta.validation.Valid;

@Controller
public class Control {
	
	@GetMapping("/")
	public String home() {
		return "add";
	}
	
	@GetMapping("/input")
	public String add(Model model) {
		model.addAttribute("student", new Student());
		return "input";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("student", student);
			return "input";
		}
		System.out.println("Student saved: " + student.getName());
		return "add";
	}
}