package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Connection.UpdateData;
import com.example.quan_ly_tuyen_bay.Controller.ChuyenBayController;
import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Model.ChuyenBay;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Model.Ve;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;

import java.awt.dnd.DropTarget;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChuyenBayRepo {

    DuongBayRepo duongBayRepo = new DuongBayRepo();
    VeRepo veRepo = new VeRepo();


    public DuongBay timDuongBay(String maDuongBay) {
        List<DuongBay> list = duongBayRepo.findAllDuongBay();

        for (DuongBay db : list) {
            if (db.getMaDuongBay().equals(maDuongBay)) {

                return db;
            }
        }

        return null;
    }

    public List<ChuyenBay> findAllChuyenBay() {

        List<ChuyenBay> list = new ArrayList<>();

        try (
//                ResultSet rs = DataConnection.retrieveData("select * from CHUYENBAY");
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from CHUYENBAY");
                ResultSet rs = ps.executeQuery();
        ) {
            Date datenow = Calendar.getInstance().getTime();
            while (rs.next()) {
                ChuyenBay cb = new ChuyenBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        timDuongBay(rs.getString(3).trim()),
                        rs.getDate(4),
                        rs.getTime(5).toLocalTime(),
                        rs.getInt(6)
                );


                cb.setVeArrayList(veRepo.findAllVe(cb.getMaChuyenBay()));

                if (cb.getTrangThai() == ChuyenBay.CONVE || cb.getTrangThai() == ChuyenBay.HETVE) {
                    if (datenow.after(cb.getCBTime())) {
                        capNhatHoanTat(cb.getMaChuyenBay());
                        cb.setTrangThai(ChuyenBay.HOANTAT);
                    }
                }

                list.add(cb);

            }
        } catch (Exception e) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, "Lỗi load table CHUYENBAY", e);
        }

        return list;
    }

    public boolean capNhatHoanTat(String maCB) {
        String splCommand = "update CHUYENBAY set TRANGTHAI = ?" + " where MACHUYENBAY=?";

//        DataConnection.createStatement();

        try (
                Connection connection =DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(splCommand);
        ) {
            ps.setInt(1, ChuyenBay.HOANTAT);
            ps.setString(2, maCB);

            System.out.println("Chuyến bay " + maCB + " hoàn tất");

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            Logger.getLogger(UpdateData.class.getName()).log(Level.SEVERE, null, e);
        }

        return false;
    }

    public boolean addChuyenBay(ChuyenBay cb) {
        String sqlCommand = "INSERT INTO CHUYENBAY VALUES(?,?,?,?,?,?)";
        System.out.println(cb.toString());
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
        ) {

            ps.setString(1, cb.getMaChuyenBay());
            ps.setString(2, cb.getSHMB());
            ps.setString(3, cb.getDuongBay().getMaDuongBay());
            ps.setDate(4, (java.sql.Date) cb.getNgayBay());
            ps.setTime(5, Time.valueOf(cb.getGioBay()));
            ps.setInt(6, cb.getTrangThai());

            if (ps.executeUpdate() > 0) {
                System.out.println("thêm chuyến bay thành công");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thêm thất bại chuyến bay");
        return false;
    }

    public boolean cancelTrip(String mcb) {
        String sqlCommand = "UPDATE CHUYENBAY SET TRANGTHAI=? WHERE MACHUYENBAY=?";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps =connection.prepareStatement(sqlCommand);
        ) {


            ps.setInt(1, ChuyenBay.HUYCHUYEN);
            ps.setString(2, mcb);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateChuyenBay(ChuyenBay cb) {
        String sqlCommand = "UPDATE CHUYENBAY SET NGAYBAY=?,GIOBAY=? WHERE MACHUYENBAY=?";
//        DataConnection.createStatement();

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
        ) {

            ps.setDate(1, (java.sql.Date) cb.getNgayBay());
            ps.setTime(2, Time.valueOf(cb.getGioBay()));
            ps.setString(3, cb.getMaChuyenBay());
            if (ps.executeUpdate() > 0) {
                System.out.println("Sửa thành công chuyến bay");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhatHetVe(String maCb) {
        String splCommand = "UPDATE CHUYENBAY SET TRANGTHAI=? WHERE MACHUYENBAY=?";
//        DataConnection.createStatement();

        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(splCommand);
        ) {

            ps.setInt(1, ChuyenBay.HETVE);
            ps.setString(2, maCb);

            System.out.println("Chuyến bay " + maCb + " hết vế");
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean capNhatConVe(String maCb) {
        String splCommand = "UPDATE CHUYENBAY SET TRANGTHAI=? WHERE MACHUYENBAY=?";
//        DataConnection.createStatement();
        try (
                Connection connection = DataConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(splCommand);
        ) {

            ps.setInt(1, ChuyenBay.CONVE);
            ps.setString(2, maCb);

            System.out.println("Chuyến bay " + maCb + " còn vế");
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
