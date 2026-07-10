package com.api.social_media.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.social_media.entity.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    List<PostLike> findByPostId(Long postId);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);
}
