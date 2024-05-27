package com.example.quan_ly_tuyen_bay.Server;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                ServerSocket serverSocket1 = new ServerSocket(20004);
                new ClientRHandleList(serverSocket1);

                ServerSocket serverSocket2 = new ServerSocket(20003);
                ClientUpdateHandleList clientUpdateHandleList = new ClientUpdateHandleList(serverSocket2);

                ServerSocket serverSocket3 = new ServerSocket(20005);
                ClientCUDHandleList clientCUDHandleList = new ClientCUDHandleList(serverSocket3, clientUpdateHandleList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }


}
