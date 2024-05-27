package com.example.quan_ly_tuyen_bay.Client;

import com.example.quan_ly_tuyen_bay.Controller.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientListenUpdate {
    ServerSocket serverSocket;

    public ClientListenUpdate(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        listen();
    }

    void listen() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();

                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                    String requet = ois.readUTF();
                    System.out.println(requet);
                    switch (requet) {
                        case "updateDataMayBay" -> {
                            MayBayController mayBayController = MayBayController.getInstance();
                            if (mayBayController != null) {
                                mayBayController.showDataMayBay();
                            }
                        }

                        case "updateDataSanBay" -> {
                            SanBayController sanBayController = SanBayController.getInstance();
                            if (sanBayController != null) {
                                sanBayController.showData();
                            }
                        }

                        case "updateDataDuongBay" -> {
                            DuongBayController duongBayController = DuongBayController.getInstance();
                            if (duongBayController != null) {
                                duongBayController.showData();
                            }
                        }

                        case "updateDataNhanVien" -> {
                            NhanVienController nhanVienController = NhanVienController.getInstance();
                            if (nhanVienController != null) {
                                nhanVienController.showData();
                            }
                        }

                        case "updateDataChuyenBay" -> {
                            ChuyenBayController chuyenBayController = ChuyenBayController.getInstance();
                            if (chuyenBayController != null) {
                                chuyenBayController.showData();
                            }


                        }

                        case "updateDataTaiKhoan" -> {
                            DangKyController dangKyController = new DangKyController();
                            if (dangKyController != null) {
                                dangKyController.loadClassDangKy();
                            }
                        }

//                        case "updateDataVe" -> {
//                            ThongTinVeController thongTinVeController = new ThongTinVeController();
//                            if (thongTinVeController != null) {
//                                thongTinVeController.loadData();
//                            }
//
////                            DsVeController dsVeController = new DsVeController();
////                            if(dsVeController != null){
////                                dsVeController.load();
////                            }
//                        }
                    }

                    ois.close();
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
