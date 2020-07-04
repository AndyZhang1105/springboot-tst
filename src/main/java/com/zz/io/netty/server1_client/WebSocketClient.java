package com.zz.io.netty.server1_client;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class WebSocketClient {

    private static String uri = "ws://localhost:9001/ws";
    private static Session session;

    private void start() {
        WebSocketContainer container = null;
        try {
            container = ContainerProvider.getWebSocketContainer();
        } catch (Exception ex) {
            System.out.println("error: " + ex);
        }
        try {
            URI r = URI.create(uri);
            session = container.connectToServer(WebClientHandler.class, r);
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();
        client.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            do {
                input = br.readLine();
                if (!input.equals("exit")) {
                    client.session.getBasicRemote().sendText("java client: " + input);
                }
            } while (!input.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}