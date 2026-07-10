package com.api.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.social_media.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByUserId(Long userId);
}
