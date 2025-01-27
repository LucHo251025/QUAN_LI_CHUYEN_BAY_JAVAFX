package com.example.quan_ly_tuyen_bay.Client;

import com.example.quan_ly_tuyen_bay.Controller.*;

import javax.swing.plaf.synth.SynthTreeUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientUpdate {

    private Socket socket;
    private ObjectInputStream ois;

    public ClientUpdate(Socket socket) {
        this.socket = socket;
        try {
            this.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Đã tạo thêm ClientUpdate");
        listenServer();
    }


    private void listenServer() {
        new Thread(() -> {
            while (true) {
                try {
                    String request = ois.readUTF();
                    System.out.println("Nhận lệnh cập nhật từ server: ClientUpdate " + request);
                    switch (request) {
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
                                System.out.println("Đã nhận lệnh updateDataTaiKhoan");
                                DangKyController dangKyController = DangKyController.getInstance();
                                if (dangKyController != null) {
                                    System.out.println("DangKyController instance is not null. Calling loadClassDangKy.");
                                    dangKyController.loadClassDangKy();
                                } else {
                                    System.err.println("DangKyController instance is null. Cannot call loadClassDangKy.");
                                }

                        }
                        case "doiMatKhau" -> {
                            DoiMatKhauController doiMatKhauController = DoiMatKhauController.getInstance();

                            if(doiMatKhauController != null){
                                doiMatKhauController.LoadTaiKhoan();
                            }
                        }

                        case "updateDataVe" -> {
//                            ThongTinVeController thongTinVeController = ThongTinVeController.getInstance();
//                            if (thongTinVeController != null) {
//                                thongTinVeController.loadData();
//                            }
//
                            DsVeController dsVeController = DsVeController.getInstance();
                            if(dsVeController != null){
                                dsVeController.LoadGhe();
                            }
                        }
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }).start();

    }
}
