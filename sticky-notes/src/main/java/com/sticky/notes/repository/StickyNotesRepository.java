package com.sticky.notes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sticky.notes.model.StickyNotes;

@Repository
public interface StickyNotesRepository extends JpaRepository<StickyNotes, Long> {
    public List<StickyNotes> findAll();
}