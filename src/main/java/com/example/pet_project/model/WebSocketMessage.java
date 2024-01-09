package com.example.pet_project.model;

public class WebSocketMessage {

    private String content;

    public WebSocketMessage() {
    }

    public WebSocketMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

