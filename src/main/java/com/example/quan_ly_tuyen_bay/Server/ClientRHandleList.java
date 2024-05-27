package com.example.quan_ly_tuyen_bay.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientRHandleList {

    private ServerSocket serverSocket;
    private List<ClientRHandle> list = new ArrayList<>();

    public ClientRHandleList(ServerSocket serverSocket
//                            ObjectOutputStream oosUpdate
    ) {
        this.serverSocket = serverSocket;
        acceptClient();
    }

    void acceptClient() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Có kết nối tới 20004");

                    list.add(new ClientRHandle(socket));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

}
