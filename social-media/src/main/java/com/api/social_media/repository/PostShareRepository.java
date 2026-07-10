package com.api.social_media.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.social_media.entity.PostShare;

public interface PostShareRepository extends JpaRepository<PostShare, Long> {

    List<PostShare> findByPostId(Long postId);

    Optional<PostShare> findByPostIdAndUserId(Long postId, Long userId);
}
