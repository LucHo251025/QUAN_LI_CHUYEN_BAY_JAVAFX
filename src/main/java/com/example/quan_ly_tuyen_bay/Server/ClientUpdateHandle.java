package com.example.quan_ly_tuyen_bay.Server;

import com.example.quan_ly_tuyen_bay.Server.Repository.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientUpdateHandle {
    private final ObjectOutputStream oos;
    Socket socket;
    String id;

    ClientUpdateHandle(Socket socket) {
        this.socket = socket;
        this.id = String.valueOf(System.currentTimeMillis());

        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendUpdate(String dsUpdate) {
        try {
            oos.writeUTF(dsUpdate);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
