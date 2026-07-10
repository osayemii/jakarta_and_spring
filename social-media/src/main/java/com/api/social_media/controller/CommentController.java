package com.api.social_media.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.CommentService;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // send parentCommentId in the body to reply to another comment, or omit/null for a top-level comment
    @PostMapping
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        comment.setId(null);
        comment.setPostId(postId);
        return commentService.addComment(comment);
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
