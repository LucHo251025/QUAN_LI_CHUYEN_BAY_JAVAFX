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

//    public void listenFromClient() {
//        new Thread(() -> {
//            while (true) {
//                try {
//                    String request = ois.readUTF();
//                    switch (request) {
//                        case "insertMayBay" -> {
//                            MayBay mb = (MayBay) ois.readObject();
//                            mayBayRepo.add(mb);
//                            sendUpdate("updateDataMayBay");
//                        }
//                        //cái này em gửi qua client đẻ cập nhật danh sach
//                        case "insertDuongBay" -> {
//                            DuongBay db = (DuongBay) ois.readObject();
//                            duongBayRepo.addDuongBay(db);
//                            sendUpdate("updateDataDuongBay");
//                        }
//                        case "insertSanBay" -> {
//                            SanBay sb = (SanBay) ois.readObject();
//                            sanBayRepo.addSayBay(sb);
//                            sendUpdate("updateDataSanBay");
//                        }
//                        case "insertChuyenBay" -> {
//                            ChuyenBay cb = (ChuyenBay) ois.readObject();
//                            chuyenBayRepo.addChuyenBay(cb);
//                            sendUpdate("updateDataChuyenBay");
//                        }
//                        case "inserTaiKhoan" -> {
//                            TaiKhoan tk = (TaiKhoan) ois.readObject();
//                            taiKhoanRepo.addTaikhoan(tk);
//                            sendUpdate("updateDataTaiKhoan");
//                        }
//                        case "inserNhanVien" -> {
//                            NhanVien nv = (NhanVien) ois.readObject();
//                            nhanVienRepo.addNhanVien(nv);
//                            sendUpdate("updateDataNhanVien");
//                        }
//                        case "insertVe" -> {
//                            ArrayList<Ve> dsVe = (ArrayList<Ve>) ois.readObject();
//                            veRepo.addVe(dsVe);
//                            sendUpdate("updateDataVe");
//                        }
//                        case "updateMayBay" -> {
//                            MayBay mbupdate = (MayBay) ois.readObject();
//                            mayBayRepo.updateMayBay(mbupdate);
//                            sendUpdate("updateDataMayBay");
//                        }
//                        case "updateSanBay" -> {
//                            SanBay sbUpdate = (SanBay) ois.readObject();
//                            sanBayRepo.updateSanBay(sbUpdate);
//                            sendUpdate("updateDataSanBay");
//                        }
//                        case "updateDuongBay" -> {
//                            DuongBay dbUpdate = (DuongBay) ois.readObject();
//                            duongBayRepo.updateDuongBay(dbUpdate);
//                            oos.writeUTF("updateDataMayBay");
//                            sendUpdate("updateDataDuongBay");
//                        }
//                        case "cancelTrip" -> {
//                            String mcb = ois.readUTF();
//                            chuyenBayRepo.cancelTrip(mcb);
//                            sendUpdate("updateDataChuyenBay");
//                        }
//                        case "updateChuyenBay" -> {
//                            ChuyenBay cbUpdate = (ChuyenBay) ois.readObject();
//                            chuyenBayRepo.updateChuyenBay(cbUpdate);
//                            sendUpdate("updateDataChuyenBay");
//                        }
//                        case "capNhatHetVe" -> {
//                            String maCbHetVe = ois.readUTF();
//                            chuyenBayRepo.capNhatHetVe(maCbHetVe);
//                            sendUpdate("updateDataChuyenBay");
//                        }
//                        case "capNhatConVe" -> {
//                            String maCbConVe = ois.readUTF();
//                            chuyenBayRepo.capNhatConVe(maCbConVe);
//                            sendUpdate("updateDataChuyenBay");
//                        }
//                        case "doiMatKhau" -> {
//                            String str = ois.readUTF();
//                            String[] splip = str.split("-");
//                            String tenDn = splip[0];
//                            String mk = splip[1];
//                            taiKhoanRepo.doiMatKhau(tenDn, mk);
//                        }
//                        case "updateNhanvien" -> {
//                            NhanVien nvUpdate = (NhanVien) ois.readObject();
//                            nhanVienRepo.uadateNhanvien(nvUpdate);
//                            sendUpdate("updateDataNhanVien");
//                        }
//                        case "deleteMayBay" -> {
//                            String shmb = ois.readUTF();
//                            mayBayRepo.deleteMayBay(shmb);
//                            oos.writeUTF("updateDataMayBay");
//                            sendUpdate("updateDataMayBay");
//                        }
//                        case "deleteSanBay" -> {
//                            String maSanBay = ois.readUTF();
//                            sanBayRepo.deleteSanBay(maSanBay);
//                            sendUpdate("updateDataSanBay");
//                        }
//                        case "deleteDuongBay" -> {
//                            String maDuongbay = ois.readUTF();
//                            duongBayRepo.deleteDuongBay(maDuongbay);
//                            sendUpdate("updateDataDuongBay");
//                        }
//                        case "deleteVe" -> {
//                            String st = ois.readUTF();
//                            String[] spl = st.split("-");
//                            String macb = spl[0];
//                            String MaGhe = spl[1];
//                            veRepo.deleteVe(macb, MaGhe);
//                        }
//                        case "deleteNhanVien" -> {
//                            String sdt = ois.readUTF();
//                            nhanVienRepo.deleteNhanvien(sdt);
//                            sendUpdate("updateDataNhanVien");
//                        }
//                        case "deleteTaiKhoan" -> {
//                            String tenDN = ois.readUTF();
//                            taiKhoanRepo.deleteTaiKhoan(tenDN);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }


}
