package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Post;
import com.api.social_media.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getFeed() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostsByUser(Long authorId) {
        return postRepository.findByAuthorIdOrderByCreatedAtDesc(authorId);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id).orElse(null);

        if (post != null) {
            post.setContent(updatedPost.getContent());
            return postRepository.save(post);
        }

        return null;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
