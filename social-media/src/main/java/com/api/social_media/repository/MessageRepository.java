package com.api.social_media.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.social_media.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByConversationIdOrderByCreatedAtAsc(Long conversationId);
}
