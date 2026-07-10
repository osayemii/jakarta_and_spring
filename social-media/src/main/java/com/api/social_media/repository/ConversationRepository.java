package com.api.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.social_media.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findByParticipantIdsContaining(Long userId);
}
