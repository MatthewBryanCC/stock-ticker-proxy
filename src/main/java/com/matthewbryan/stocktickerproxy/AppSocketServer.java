package com.matthewbryan.stocktickerproxy;

import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import java.net.InetSocketAddress;

public class AppSocketServer {
    private static final int PORT = 443;
    private MyWebSocketServer server;
    public AppSocketServer() {
        server = new MyWebSocketServer(new InetSocketAddress(PORT));
    }

    public void EmitMessage(String message) {
        WebSocket[] clientList = server.getClientList();
        for (WebSocket client : clientList) {
            if (client != null && client.isOpen()) {
                client.send(message);
            }
        }
    }
}

class MyWebSocketServer extends WebSocketServer {

    private WebSocket[] clientList = new WebSocket[1024];

    public MyWebSocketServer(InetSocketAddress address) {
        super(address);
        try {
            this.start();
            System.out.println("WebSocket server started on port: " + address.getPort());
        } catch (Exception e) {
            System.err.println("Error starting WebSocket server: " + e.getMessage());
        }
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully.");
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("WebSocket error: " + ex.getMessage());
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
        // Add the new connection to the client list
        for (int i = 0; i < clientList.length; i++) {
            if (clientList[i] == null) {
                clientList[i] = conn; // Add the connection to the list
                break;
            }
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        for (int i = 0; i < clientList.length; i++) {
            if (clientList[i] == conn) {
                clientList[i] = null; // Remove the connection from the list
                System.out.println("Closed connection: " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
                break;
            }
        }
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message: " + message);
        // Echo the message back to the client
        conn.send("Echo: " + message);
    }

    public WebSocket[] getClientList() {
        return clientList;
    }
    public void setClientList(WebSocket[] clientList) {
        this.clientList = clientList;
    }
}