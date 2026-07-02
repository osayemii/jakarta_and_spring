package com.sticky.notes.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticky.notes.model.StickyNotes;
import com.sticky.notes.repository.StickyNotesRepository;

@Service
public class StickyNotesService {
    @Autowired
    private StickyNotesRepository notesRepo;

    public List<StickyNotes> findAll() {
        List<StickyNotes> notes = notesRepo.findAll();
        return notes;
    }

    public void saveNotes(StickyNotes note) {
        notesRepo.save(note);
    }

    public void saveEditedNotes(StickyNotes note, Long id) {
        StickyNotes editNote = notesRepo.findById(id).get();
        editNote.setTitle(note.getTitle());
        editNote.setContent(note.getContent());
        notesRepo.save(editNote);
    }

    public void deleteNotes(StickyNotes notes) {
        notesRepo.delete(notes);
    }
}