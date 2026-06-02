package com.validation.test.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.validation.test.model.*;

@RestController
public class Controller {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@PostMapping("/add")
	public String add(Model model) {
		model.addAttribute("model", new Student());
		return "add";
	}
	
	@PostMapping("/save")
	public String save() {
		return "add";
	}
}