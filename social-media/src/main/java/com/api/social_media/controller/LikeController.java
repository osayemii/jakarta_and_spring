package com.api.social_media.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.LikeService;

@RestController
@RequestMapping("/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public PostLike like(@PathVariable Long postId, @RequestBody Map<String, Long> body) {
        return likeService.like(postId, body.get("userId"));
    }

    @DeleteMapping
    public void unlike(@PathVariable Long postId, @RequestParam Long userId) {
        likeService.unlike(postId, userId);
    }

    @GetMapping
    public List<PostLike> getLikes(@PathVariable Long postId) {
        return likeService.getLikes(postId);
    }

    @GetMapping("/me")
    public Map<String, Boolean> hasLiked(@PathVariable Long postId, @RequestParam Long userId) {
        return Map.of("liked", likeService.hasLiked(postId, userId));
    }
}
