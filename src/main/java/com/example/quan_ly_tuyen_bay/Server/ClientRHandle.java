package com.example.quan_ly_tuyen_bay.Server;

import com.example.quan_ly_tuyen_bay.Model.*;
import com.example.quan_ly_tuyen_bay.Server.Repository.*;
import com.itextpdf.text.Paragraph;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientRHandle {
    MayBayRepo mayBayRepo = new MayBayRepo();
    SanBayRepo sanBayRepo = new SanBayRepo();
    DuongBayRepo duongBayRepo = new DuongBayRepo();
    VeRepo veRepo = new VeRepo();
    NhanVienRepo nhanVienRepo = new NhanVienRepo();
    TaiKhoanRepo taiKhoanRepo = new TaiKhoanRepo();
    ChuyenBayRepo chuyenBayRepo = new ChuyenBayRepo();
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    Socket socket;
    String id;

    ClientRHandle(Socket socket) {
        this.socket = socket;
        this.id = String.valueOf(System.currentTimeMillis());

        try {
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());

            System.out.println("Lay IO xong");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Lang nghe request");
        listenFromClient();
    }

    public void listenFromClient() {
        new Thread(() -> {
            while (true) {
                try {

                    String request = ois.readUTF();
//                    String[] parts = cmd.split("-");
//                    String request = parts[0];
//
//                    String id = parts.length > 1 ? parts[1] : null; // Sử dụng null nếu không có phần id
                    System.out.println("Nhan lenh " + request);
                    switch (request) {
                        case "getMayBayList" -> {
                            oos.writeObject(mayBayRepo.findAllMayBay());
                            oos.flush();
                            oos.reset();
                        }
                        case "getSanBayList" -> {
                            oos.writeObject(sanBayRepo.findAllSanBay());
                            oos.flush();
                            oos.reset();
                        }
                        case "getDuongBayList" -> {
                            oos.writeObject(duongBayRepo.findAllDuongBay());
                            oos.flush();
                            oos.reset();
                        }
                        case "getVeList" -> {
                            oos.writeObject(veRepo.findAllVe(id));
                            oos.flush();
                            oos.reset();
                        }
                        case "getNhanVienList" -> {
                            oos.writeObject(nhanVienRepo.findAllNhanVien());
                            oos.flush();
                            oos.reset();
                        }
                        case "getTaiKhoanList" -> {
                            oos.writeObject(taiKhoanRepo.findAllTaiKhoan());
                            oos.flush();
                            oos.reset();
                        }
                        case "getChuyenBayList" -> {
                            oos.writeObject(chuyenBayRepo.findAllChuyenBay());
                            oos.flush();
                            oos.reset();
                        }
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                            if (cmd.equals("deleteMayBay")) {
//                                // XU ly Delete
//
//                                // gui thong bao ds may bay dc cap nhat
//                                // clientUpdateList.sendUpdate("DSMayBay)
//                            }

                System.out.println("aaaa");
            }
        }).start();
    }
}
