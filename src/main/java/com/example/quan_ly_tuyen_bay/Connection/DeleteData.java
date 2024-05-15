package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Model.SanBay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteData {

    public static boolean deleteMayBay(String shmb){
        String sqlCommad = "DELETE FROM MAYBAY WHERE SHMB =?";
        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommad);

            ps.setString(1,shmb);

            if(ps.executeUpdate() > 0){
                System.out.println("Xóa thành công máy bay");
                return true;
            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi xóa máy bay",e);
        }
        return false;
    }

    public static boolean deleteSanBay(String maSanBay){
        String sqlCommand = "DELETE FROM SANBAY WHERE MASANBAY =?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,maSanBay);

            if(ps.executeUpdate() > 0){
                System.out.println("Xóa sân bay thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDuongBay(String maDuongbay){
        String sqlCommand = "DELETE FROM DUONGBAY WHERE MADUONGBAY=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,maDuongbay);

            if(ps.executeUpdate() > 0){
                System.out.println("Xóa thành công duong bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public static boolean deleteVe(String maCB,String MaGhe){
        String sqlCommand = "DELETE FROM VE WHERE MACB=? AND MAGHE=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,maCB);
            ps.setString(2,MaGhe);

            if(ps.executeUpdate() > 0){
                System.out.println("Hủy vé thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteNhanVien(String sdt){
        String sqlCommand = "DELETE FROM NHANVIEN WHERE SDT=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,sdt);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xóa nhân viên thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean deleteTaiKhoan(String tenDN){
        String sqlCommand = "DELETE FROM TAIKHOAN WHERE TENDANGNHAP=?";

        try {
             DataConnection.createStatement();
             PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

             ps.setString(1,tenDN);

            if (ps.executeUpdate() > 0) {
                System.out.println("Xóa tài khoản thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
