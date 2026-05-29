package com.sticky.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sticky.notes.model.StickyNotes;
import jakarta.validation.Valid;

@Controller
public class StickyNotesController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/addnotes")
    public String addNotes() {
        return "addnotes";
    }

    @PostMapping("/saveNotes")
    public String submitNotes(@Valid StickyNotes notes, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid format! The note title must be in the format <3 digits>_<10alphanumeric characters>. For example, 010_Note10");
            return "addnotes";
        } else {
            System.out.println(notes.toString());
            return "addnotes";
        }
    }

    @GetMapping("/viewnote")
    public String viewNotes() {
        return "viewnote";
    }

    @GetMapping("/after_login_dashboard") // New mapping for after login dashboard
    public String afterLoginDashboard() {
        return "dashboard";
    }
}
