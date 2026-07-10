package com.api.social_media.entity;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private Long authorId;
    private Long parentCommentId; // null for a top-level comment, set for a reply
    private String content;
    private long createdAt;

    public Comment() {
    }

    public Comment(Long postId, Long authorId, String content, Long parentCommentId) {
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.createdAt = System.currentTimeMillis();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public Long getParentCommentId() { return parentCommentId; }
    public void setParentCommentId(Long parentCommentId) { this.parentCommentId = parentCommentId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
