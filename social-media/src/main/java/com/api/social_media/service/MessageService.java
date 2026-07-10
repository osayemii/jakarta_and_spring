package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Message;
import com.api.social_media.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationService conversationService;

    public MessageService(MessageRepository messageRepository, ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.conversationService = conversationService;
    }

    public Message sendMessage(Message message) {
        Message saved = messageRepository.save(message);
        conversationService.touchLastMessageAt(message.getConversationId());
        return saved;
    }

    public List<Message> getMessages(Long conversationId) {
        return messageRepository.findByConversationIdOrderByCreatedAtAsc(conversationId);
    }
}
