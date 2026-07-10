package com.api.social_media.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // "group" alone would become a column literally named `group`, which is a
    // reserved SQL keyword in MySQL (GROUP BY) - that silently broke CREATE TABLE.
    @Column(name = "is_group")
    private boolean group;
    private String title; // only used for group chats

    @ElementCollection
    private List<Long> participantIds;

    private long createdAt;
    private long lastMessageAt;

    public Conversation() {
    }

    public Conversation(boolean group, String title, List<Long> participantIds) {
        this.group = group;
        this.title = title;
        this.participantIds = participantIds;
        this.createdAt = System.currentTimeMillis();
        this.lastMessageAt = this.createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isGroup() { return group; }
    public void setGroup(boolean group) { this.group = group; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Long> getParticipantIds() { return participantIds; }
    public void setParticipantIds(List<Long> participantIds) { this.participantIds = participantIds; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }

    public long getLastMessageAt() { return lastMessageAt; }
    public void setLastMessageAt(long lastMessageAt) { this.lastMessageAt = lastMessageAt; }
}
