package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Comment;
import com.api.social_media.entity.Post;
import com.api.social_media.repository.CommentRepository;
import com.api.social_media.repository.PostRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment addComment(Comment comment) {
        Comment saved = commentRepository.save(comment);

        Post post = postRepository.findById(comment.getPostId()).orElse(null);
        if (post != null) {
            post.setCommentCount(post.getCommentCount() + 1);
            postRepository.save(post);
        }

        return saved;
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return;
        }
        commentRepository.deleteById(id);

        Post post = postRepository.findById(comment.getPostId()).orElse(null);
        if (post != null) {
            post.setCommentCount(Math.max(0, post.getCommentCount() - 1));
            postRepository.save(post);
        }
    }
}
