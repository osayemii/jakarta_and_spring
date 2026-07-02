package com.sticky.notes.model;

import jakarta.persistence.*;

import com.sticky.notes.validation.NoteTitle;

@Entity
@Table(name = "notes")
public class StickyNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NoteTitle
    @Column
    private String title;

    @Column
    private String content;

    public StickyNotes() {
        super();
    }

    public StickyNotes(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "StickyNotes [title=" + title + ", content=" + content + "]";
    }
}
