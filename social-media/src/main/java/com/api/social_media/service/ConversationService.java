package com.api.social_media.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.social_media.entity.Conversation;
import com.api.social_media.repository.ConversationRepository;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationService(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    // For a 1:1 chat (group=false), reuses an existing conversation between the
    // same two participantIds if one already exists instead of creating a duplicate.
    public Conversation createOrGetOneToOne(Conversation request) {
        List<Long> participantIds = request.getParticipantIds();

        if (!request.isGroup() && participantIds != null && participantIds.size() == 2) {
            List<Conversation> existing = conversationRepository.findByParticipantIdsContaining(participantIds.get(0));
            for (Conversation c : existing) {
                if (!c.isGroup() && c.getParticipantIds() != null
                        && c.getParticipantIds().size() == 2
                        && c.getParticipantIds().containsAll(participantIds)) {
                    return c;
                }
            }
        }

        return conversationRepository.save(request);
    }

    public Conversation createGroup(Conversation request) {
        return conversationRepository.save(request);
    }

    public List<Conversation> getConversationsForUser(Long userId) {
        return conversationRepository.findByParticipantIdsContaining(userId);
    }

    public Conversation getConversationById(Long id) {
        return conversationRepository.findById(id).orElse(null);
    }

    public void touchLastMessageAt(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId).orElse(null);
        if (conversation != null) {
            conversation.setLastMessageAt(System.currentTimeMillis());
            conversationRepository.save(conversation);
        }
    }
}
