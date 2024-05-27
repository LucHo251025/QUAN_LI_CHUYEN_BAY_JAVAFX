package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Client.ClientCRUD;
import com.example.quan_ly_tuyen_bay.Model.*;

import java.util.ArrayList;
import java.util.List;

public class InsertData {

    public static void insertMayBay(MayBay mb) {
        ClientCRUD.sendRequestObject("insertMayBay", mb);
    }

    public static void insertSanBay(SanBay sb) {
        ClientCRUD.sendRequestObject("insertSanBay", sb);
    }

    public static void insertDuongBay(DuongBay db) {
        ClientCRUD.sendRequestObject("insertDuongBay", db);
    }

    public static void insertChuyenBay(ChuyenBay cb) {
        ClientCRUD.sendRequestObject("insertChuyenBay", cb);
    }

    public static void inserTaiKhoan(TaiKhoan taikhoan) {
        ClientCRUD.sendRequestObject("inserTaiKhoan", taikhoan);
    }

    public static void inserNhanVien(NhanVien nv) {
        ClientCRUD.sendRequestObject("inserNhanVien", nv);
    }

    public static void insertVe(List<Ve> dsVe) {
        ClientCRUD.sendRequestObject("insertVe", dsVe);
        System.out.println("Đang gửi yêu cầu thêm vé");
    }
}
