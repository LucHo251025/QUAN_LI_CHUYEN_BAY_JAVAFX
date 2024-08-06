package com.example.quan_ly_tuyen_bay.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientCUDHandleList {

    private ServerSocket serverSocket;
    private ClientUpdateHandleList clientUpdateHandleList;
    //    private ObjectOutputStream oosUpdate;
    private List<ClientCUDHandle> list = new ArrayList<>();

    public ClientCUDHandleList(ServerSocket serverSocket,
                               ClientUpdateHandleList clientUpdateHandleList) {
        this.serverSocket = serverSocket;
        this.clientUpdateHandleList = clientUpdateHandleList;

        acceptClient();
    }

    void acceptClient() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Có kết nối tới 20005");

                    list.add(new ClientCUDHandle(socket) {

                        @Override
                        public void sendUpdate(String str) {
                            sendUpdate1(str);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void sendUpdate1(String dsUpdate) {
        clientUpdateHandleList.sendUpdateToAllClient(dsUpdate);
    }


}
