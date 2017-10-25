package com.kwintenp.swapibackend;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketModule {
    private static final Logger log = LoggerFactory.getLogger(SocketModule.class);
    private SocketIOServer server;


    @Autowired
    public SocketModule(SocketIOServer server) {
        this.server = server;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("action", ActionDTO.class, onactionReceived());
    }

    private DataListener<ActionDTO> onactionReceived() {
        return (client, data, ackSender) -> {
            log.error("Client[{}] - Received action message '{}'", client.getSessionId().toString(), data);
            server.getBroadcastOperations().sendEvent("action", data);
        };
    }

    private ConnectListener onConnected() {
        log.info("called");
        return client -> {
            HandshakeData handshakeData = client.getHandshakeData();
            log.error("Client[{}] - Connected to action module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.error("Client[{}] - Disconnected from action module.", client.getSessionId().toString());
        };
    }
}