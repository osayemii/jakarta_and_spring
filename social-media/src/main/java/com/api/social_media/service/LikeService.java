package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Post;
import com.api.social_media.entity.PostLike;
import com.api.social_media.repository.PostLikeRepository;
import com.api.social_media.repository.PostRepository;

@Service
public class LikeService {

    private final PostLikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeService(PostLikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    public PostLike like(Long postId, Long userId) {
        PostLike like = new PostLike(postId, userId);
        PostLike saved = likeRepository.save(like);

        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
        }

        return saved;
    }

    public void unlike(Long postId, Long userId) {
        likeRepository.findByPostIdAndUserId(postId, userId).ifPresent(likeRepository::delete);

        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setLikeCount(Math.max(0, post.getLikeCount() - 1));
            postRepository.save(post);
        }
    }

    public List<PostLike> getLikes(Long postId) {
        return likeRepository.findByPostId(postId);
    }

    public boolean hasLiked(Long postId, Long userId) {
        return likeRepository.findByPostIdAndUserId(postId, userId).isPresent();
    }
}
