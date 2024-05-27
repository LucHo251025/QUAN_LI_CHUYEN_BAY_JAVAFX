package com.example.quan_ly_tuyen_bay.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCRUD {
    private  Socket socket;
    private static ObjectOutputStream oos;

    public ClientCRUD(Socket socket) {
        this.socket = socket;
        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void sendRequestObject(String request, T object){
        try {
            oos.writeUTF(request);
            oos.flush();
            oos.writeObject(object);
            oos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  static void sendRequestid(String requet, String id){
        try {
            oos.writeUTF(requet);
            oos.flush();
            oos.writeUTF(id);
            oos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
