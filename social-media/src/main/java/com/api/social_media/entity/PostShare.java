package com.api.social_media.entity;

import jakarta.persistence.*;

@Entity
public class PostShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private Long userId;
    private String comment; // optional caption for a quote-repost
    private long createdAt;

    public PostShare() {
    }

    public PostShare(Long postId, Long userId, String comment) {
        this.postId = postId;
        this.userId = userId;
        this.comment = comment;
        this.createdAt = System.currentTimeMillis();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
