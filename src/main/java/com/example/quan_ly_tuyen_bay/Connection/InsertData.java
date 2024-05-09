package com.example.quan_ly_tuyen_bay.Connection;

import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import com.example.quan_ly_tuyen_bay.Model.SanBay;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.logging.Logger;

public class InsertData {

    public static boolean insertMayBay(MayBay mb){
        String sqlCommand = "INSERT INTO MAYBAY VALUE(?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps =DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,mb.getSHMB());
            ps.setString(2,mb.getHangBay());
            ps.setInt(3,mb.getSoGhe());

            if(ps.executeUpdate() > 0){
                System.out.println("Thêm thành công máy bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertSanBay(SanBay sb){
        String sqlCommand = "INSERT INTO SANBAY VALUE(?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps =DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,sb.getMaSanBay());
            ps.setString(2,sb.getTenSanBay());
            ps.setString(3,sb.getDiaDiem());

            if(ps.executeUpdate() > 0){
                System.out.println("Thêm sân bay thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertDuongBay(DuongBay db){
        String sqlCommand ="INSERT INTO DUONGBAY VALUE(?,?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

            ps.setString(1,db.getMaDuongBay());
            ps.setString(2,db.getMaSanBayDi());
            ps.setString(3,db.getMaSanBayDen());
            ps.setInt(4,db.getKhoangCach());

            if(ps.executeUpdate() > 0){
                System.out.println("Thêm thành công đường bay");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean insertChuyenBay(ChuyenBay cb){
        String sqlCommand = "INSERT INTO CHUYENBAY VALUES(?,?,?,?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);
            ps.setString(1,cb.getMaChuyenBay());
            ps.setString(2,cb.getSHMB());
            ps.setString(3,cb.getDuongBay().getMaDuongBay());
            ps.setDate(4, (Date) cb.getNgayBay());
            ps.setTime(5, Time.valueOf(cb.getGioBay()));
            ps.setInt(6,cb.getTrangThai());

            if (ps.executeUpdate() > 0) {
                System.out.println("thêm chuyến bay thành công");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Thêm thất bại chuyến bay");
        return false;
    }
}
