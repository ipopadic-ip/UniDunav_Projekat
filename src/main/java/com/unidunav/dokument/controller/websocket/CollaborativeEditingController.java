package com.unidunav.dokument.controller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.unidunav.dokument.dto.websocket.EditMessage;
import com.unidunav.dokument.dto.websocket.TypingStatus;

@Controller
public class CollaborativeEditingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @MessageMapping("/edit")
    public void editContent(@Payload EditMessage message, SimpMessageHeaderAccessor accessor) {
        String email = (String) accessor.getSessionAttributes().get("user");
        message.setAutorEmail(email != null ? email : "nepoznat"); // backend unosi autorEmail
        System.out.println("Edit poruka od: " + email + " | Sadržaj: " + message.getSadrzaj());
        messagingTemplate.convertAndSend("/topic/edit/" + message.getVerzijaId(), message);
    }

    @MessageMapping("/typing")
    public void typing(@Payload TypingStatus status, SimpMessageHeaderAccessor accessor) {
        String email = (String) accessor.getSessionAttributes().get("user");
        status.setAutorEmail(email); // backend unosi autorEmail
        messagingTemplate.convertAndSend("/topic/typing/" + status.getVerzijaId(), status);
    }

    // Prijem izmene sadržaja
//    @MessageMapping("/edit") // klijent šalje na /app/edit
//    @PreAuthorize("hasAnyRole('SLUZBENIK','ADMIN')")
//    public void editContent(@Payload EditMessage message) {
//        messagingTemplate.convertAndSend("/topic/edit/" + message.getVerzijaId(), message);
//    }
//
//    // Prijem statusa "kuca"
//    @MessageMapping("/typing")
//    @PreAuthorize("hasAnyRole('SLUZBENIK','ADMIN')")
//    public void typing(@Payload TypingStatus status) {
//        messagingTemplate.convertAndSend("/topic/typing/" + status.getVerzijaId(), status);
//    }
}
