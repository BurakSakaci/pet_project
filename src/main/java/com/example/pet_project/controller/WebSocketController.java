package com.example.pet_project.controller;

import com.example.pet_project.model.WebSocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/user-created")
    @SendTo("/topic/user-created")
    public WebSocketMessage handleUserCreated(WebSocketMessage message) {
        return new WebSocketMessage("New user created: " + message.getContent());
    }

    @MessageMapping("/post-created")
    @SendTo("/topic/post-created")
    public WebSocketMessage handlePostCreated(WebSocketMessage message) {
        return new WebSocketMessage("New post created: " + message.getContent());
    }
}

