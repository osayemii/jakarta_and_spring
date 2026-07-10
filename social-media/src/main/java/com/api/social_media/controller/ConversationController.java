package com.api.social_media.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.ConversationService;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    // For a 1:1 chat: {"group": false, "participantIds": [1, 2]}
    // For a group chat: {"group": true, "title": "...", "participantIds": [1, 2, 3]}
    @PostMapping
    public Conversation createOrGet(@RequestBody Conversation request) {
        request.setId(null);
        return request.isGroup() ? conversationService.createGroup(request) : conversationService.createOrGetOneToOne(request);
    }

    @GetMapping
    public List<Conversation> getMyConversations(@RequestParam Long userId) {
        return conversationService.getConversationsForUser(userId);
    }

    @GetMapping("/{id}")
    public Conversation getConversation(@PathVariable Long id) {
        return conversationService.getConversationById(id);
    }
}
