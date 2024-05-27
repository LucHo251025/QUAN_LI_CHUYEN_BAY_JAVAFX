package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Client.ClientCRUD;
import com.example.quan_ly_tuyen_bay.Model.SanBay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteData {

    public static void deleteMayBay(String shmb){
        ClientCRUD.sendRequestid("deleteMayBay",shmb);
    }

    public static void deleteSanBay(String maSanBay){
        ClientCRUD.sendRequestid("deleteSanBay",maSanBay);
    }

    public static void deleteDuongBay(String maDuongbay){
        ClientCRUD.sendRequestid("deleteDuongBay",maDuongbay);

    }


    public static void deleteVe(String maCB,String MaGhe){
        ClientCRUD.sendRequestid("deleteVe",maCB+"-"+MaGhe);
    }

    public static void deleteNhanVien(String sdt){
        ClientCRUD.sendRequestid("deleteNhanVien",sdt);
    }

    public static void deleteTaiKhoan(String tenDN){
        ClientCRUD.sendRequestid("deleteTaiKhoan",tenDN);
    }
}
