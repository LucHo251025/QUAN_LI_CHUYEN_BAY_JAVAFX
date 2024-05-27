package com.example.quan_ly_tuyen_bay.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientUpdateHandleList {

    private ServerSocket serverSocket;
    private List<ClientUpdateHandle> list = new ArrayList<>();

    public ClientUpdateHandleList(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        acceptClient();
    }

    void acceptClient() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Có kết nối tới 20003");

                    list.add(new ClientUpdateHandle(socket));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void sendUpdateToAllClient(String dsUpdate) {
        System.out.println("20003 gửi tới " + list.size() + "client");
        list.forEach(clientUpdateHandle -> {
            clientUpdateHandle.sendUpdate(dsUpdate);
        });
    }
}
