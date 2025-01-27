package com.example.quan_ly_tuyen_bay.Connection;


import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Model.*;
import com.example.quan_ly_tuyen_bay.Server.Repository.AES;
import javafx.scene.control.cell.ComboBoxTreeCell;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadData {

    private static final String ADDRESS = "localhost";
    private static final int PORT = 20004;
    private static Socket socket;

    private static final ObjectInputStream ois;

    private static final ObjectOutputStream oos;

    static {
        try {
            socket = new Socket(ADDRESS, PORT);

            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();


            System.out.println("Đã tạo thêm kết nối 2004");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//<T>: Đây là một kiểu tham số tổng quát.
// Nó cho phép phương thức trả về một danh sách của bất kỳ loại nào (T).

    public static <T> List<T> sendCommand(String commad) {
        List<T> dataList = new ArrayList<>();

        try {

            oos.writeUTF(commad);
            oos.flush();
            oos.reset();

            Object receivedObject = ois.readObject();

            if (receivedObject instanceof List) {
                dataList = (List<T>) receivedObject;
            } else {
                throw new Exception("Nhận được dữ liệu không mong đợi từ máy");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public static void loadTableMayBay() {
        Controller.mayBayArrayList.clear();
        List<MayBay> list = sendCommand("getMayBayList");
        Controller.mayBayArrayList.addAll(list);
    }

    public static void loadTableSanBay() {
        Controller.sanBayArrayList.clear();
        List<SanBay> list = sendCommand("getSanBayList");
        Controller.sanBayArrayList.addAll(list);
    }

    public static void loadTableDuongBay() {
        Controller.duongBayArrayList.clear();
        List<DuongBay> list = sendCommand("getDuongBayList");
        Controller.duongBayArrayList.addAll(list);


    }

    public static void loadTableChuyenBay() {
        Controller.chuyenBayArrayList.clear();
        List<ChuyenBay> list = sendCommand("getChuyenBayList");
        Controller.chuyenBayArrayList.addAll(list);
        for (ChuyenBay cb : Controller.chuyenBayArrayList) {
            if (cb.getMaChuyenBay().equals(Controller.cb.getMaChuyenBay())) {
                Controller.cb = cb;
                break;
            }
        }
    }


//    public static ArrayList<Ve> loadTableVe(String maChuyenBay) {
//        List<Ve> list = sendCommand("getVeList-" + maChuyenBay);
//        return (ArrayList<Ve>) list;
//    }

    public static void loadTableTaiKhoan() {
        try {
            Controller.taiKhoanArrayList.clear();
            System.out.println("Đang load tài khoản" );
            SecretKey secretKey = AES.getStaticKey();

            List<TaiKhoan> list = sendCommand("getTaiKhoanList");

            System.out.println(list.size());
            for (TaiKhoan tk : list) {
                String giaiMa=AES.decrypt(tk.getMatKhau(), secretKey);
                tk.setMatKhau(giaiMa);
                Controller.taiKhoanArrayList.add(tk);
            }

            for (TaiKhoan tk : Controller.taiKhoanArrayList){
                System.out.println("Load Data TK:"+tk.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadTableNhanVien() {
        Controller.nhanVienArrayList.clear();
        List<NhanVien> list = sendCommand("getNhanVienList");
        Controller.nhanVienArrayList.addAll(list);
    }

    public LoadData() {

        Controller.duongBayArrayList.clear();
        Controller.sanBayArrayList.clear();
        Controller.mayBayArrayList.clear();
        Controller.chuyenBayArrayList.clear();
        Controller.nhanVienArrayList.clear();
        Controller.taiKhoanArrayList.clear();

        loadTableDuongBay();
        loadTableSanBay();
        loadTableMayBay();
        loadTableChuyenBay();
        loadTableNhanVien();
        loadTableTaiKhoan();


//        try {
//            socket = new Socket(ADDRESS, PORT);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
