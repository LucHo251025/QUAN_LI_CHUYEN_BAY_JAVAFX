package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Controller.DuongBayController;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import com.example.quan_ly_tuyen_bay.Model.SanBay;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateData {
    public static boolean capNhatHoanTat(String maCB){
        String splCommand = "update CHUYENBAY set TRANGTHAI = ?" +" where MACHUYENBAY=?";


        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(splCommand);
            ps.setInt(1, ChuyenBay.HOANTAT);
            ps.setString(2,maCB);

            System.out.println("Chuyến bay "+maCB+" hoàn tất");

            return ps.executeUpdate() > 0;
        }catch (Exception e){
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    public static boolean updateMayBay(MayBay mb){
        String sqlCommand = "update MAYBAY set SOGHE= ?"+" where SHMB=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps= DataConnection.connection.prepareStatement(sqlCommand);

            ps.setInt(1,mb.getSoGhe());
            ps.setString(2,mb.getSHMB());

            if(ps.executeUpdate() > 0){
                System.out.println("Sửa thành công máy bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean updateSanBay(SanBay sb){
        String sqlCommand = "UPDATE SANBAY SET TENSANBAY=?,DIADIEM=? WHERE MASANBAY=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,sb.getTenSanBay());
            ps.setString(2,sb.getDiaDiem());
            ps.setString(3,sb.getMaSanBay());

            if(ps.executeUpdate() > 0){
                System.out.println("Sửa thành công sân bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateDuongBay(DuongBay db){
        String sqlCommad ="UPDATE DUONGBAY SET KHOANGCACH=? WHERE MADUONGBAY=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommad);

            ps.setInt(1,db.getKhoangCach());
            ps.setString(2,db.getMaDuongBay());

            if(ps.executeUpdate() > 0){
                System.out.println("Sửa thành công đường bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean cancelTrip(String mcb){
        String sqlCommand = "UPDATE CHUYENBAY SET TRANGTHAI=? WHERE MACHUYENBAY=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setInt(1,ChuyenBay.HUYCHUYEN);
            ps.setString(2,mcb);

            return ps.executeUpdate() >0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean updateChuyenBay(ChuyenBay cb){
        String sqlCommand = "UPDATE CHUYENBAY SET NGAYBAY=?,GIOBAY=? WHERE MACHUYENBAY=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setDate(1,(Date) cb.getNgayBay());
            ps.setTime(2, Time.valueOf(cb.getGioBay()));
            ps.setString(3,cb.getMaChuyenBay());
            if(ps.executeUpdate() > 0){
                System.out.println("Sửa thành công chuyến bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
