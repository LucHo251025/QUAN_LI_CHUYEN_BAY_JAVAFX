package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Controller.Controller;
import com.example.quan_ly_tuyen_bay.Controller.DuongBayController;
import com.example.quan_ly_tuyen_bay.Model.DuongBay;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DuongBayRepo {
    public List<DuongBay> findAllDuongBay(){
        List<DuongBay> list = new ArrayList<>();
        ResultSet rs = DataConnection.retrieveData("select * from DUONGBAY");

        try {
            while (rs.next()){
                DuongBay duongBay = new DuongBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        rs.getInt(4)
                );

                list.add(duongBay);
            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi load table DUONGBAY",e);
        }

        return list;
    }

    public boolean addDuongBay(DuongBay db){
        String sqlCommand ="INSERT INTO DUONGBAY VALUE(?,?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps =DataConnection.connection.prepareStatement(sqlCommand);

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

    public boolean updateDuongBay(DuongBay db){
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

    public boolean deleteDuongBay(String maDuongbay){
        String sqlCommand = "DELETE FROM DUONGBAY WHERE MADUONGBAY=?";

        try {
            DataConnection.createStatement();
            PreparedStatement ps =DataConnection.connection.prepareStatement(sqlCommand);

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
}
