package com.matthewbryan.stocktickerproxy;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle incoming messages from the WebSocket client
        System.out.println("Received message: " + message.getPayload());

        // Echo the message back to the client
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket connection established: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("WebSocket connection closed: " + session.getId());
    }
}