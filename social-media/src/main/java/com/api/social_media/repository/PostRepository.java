package com.api.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.social_media.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByAuthorIdOrderByCreatedAtDesc(Long authorId);

    List<Post> findAllByOrderByCreatedAtDesc();
}
