package com.api.social_media.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        post.setId(null);
        return postService.savePost(post);
    }

    @GetMapping
    public List<Post> getFeed() {
        return postService.getFeed();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/user/{authorId}")
    public List<Post> getPostsByUser(@PathVariable Long authorId) {
        return postService.getPostsByUser(authorId);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
