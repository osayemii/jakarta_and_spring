package com.api.social_media.controller;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.api.social_media.entity.*;
import com.api.social_media.service.MessageService;

@RestController
@RequestMapping("/conversations/{conversationId}/messages")
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    // Saves the message, then instantly pushes it to everyone subscribed to
    // /topic/conversations/{conversationId} - no polling needed on the client.
    @PostMapping
    public Message sendMessage(@PathVariable Long conversationId, @RequestBody Message message) {
        message.setId(null);
        message.setConversationId(conversationId);
        Message saved = messageService.sendMessage(message);
        messagingTemplate.convertAndSend("/topic/conversations/" + conversationId, saved);
        return saved;
    }

    @GetMapping
    public List<Message> getMessages(@PathVariable Long conversationId) {
        return messageService.getMessages(conversationId);
    }
}
