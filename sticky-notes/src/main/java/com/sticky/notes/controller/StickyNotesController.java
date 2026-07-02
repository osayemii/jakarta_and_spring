package com.sticky.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sticky.notes.model.StickyNotes;
import com.sticky.notes.service.StickyNotesService;

import jakarta.validation.Valid;

@Controller
public class StickyNotesController {

    @Autowired
    private StickyNotesService noteService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/addnotes")
    public String addNotes() {
        return "addNotes";
    }

    @PostMapping("/saveNotes")
    public String submitNotes(@Valid StickyNotes notes,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage",
                    "Invalid format! The note title must be in the format <3 digits>_<10 alphanumeric characters>. For example, 010_Note10");
            return "addNotes";
        } else {
            noteService.saveNotes(notes);
            return "addNotes";
        }
    }

    @GetMapping("/viewnote")
    public String viewNotes(Model model) {
        List<StickyNotes> notesList = noteService.findAll();
        model.addAttribute("notes", notesList);
        model.addAttribute("notesJson", toNotesJson(notesList));
        return "viewNote";
    }

    private String toNotesJson(List<StickyNotes> notesList) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < notesList.size(); i++) {
            StickyNotes note = notesList.get(i);
            if (i > 0) {
                json.append(",");
            }
            json.append("{\"id\":").append(note.getId())
                .append(",\"title\":\"").append(escapeJson(note.getTitle()))
                .append("\",\"content\":\"").append(escapeJson(note.getContent()))
                .append("\"}");
        }
        json.append("]");
        return json.toString();
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }

    @PostMapping("/editnote/{id}")
    public String editNotes(@PathVariable("id") Long id, @ModelAttribute StickyNotes notes,
            RedirectAttributes redirectAttributes) {
        noteService.saveEditedNotes(notes, id);
        return "redirect:/viewnote";
    }

    @PostMapping("/deletenote/{id}")
    public String deleteNotes(@PathVariable("id") Long id, @ModelAttribute StickyNotes notes) {
        notes.setId(id);
        noteService.deleteNotes(notes);
        return "redirect:/viewnote";
    }

    @GetMapping("/after_login_dashboard") // New mapping for after login dashboard
    public String afterLoginDashboard() {
        return "dashboard";
    }
}
