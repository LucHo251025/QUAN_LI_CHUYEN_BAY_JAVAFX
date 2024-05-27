package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.MayBay;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class  MayBayRepo {
    public List<MayBay> findAllMayBay() {
        List<MayBay> list = new ArrayList<>();
        ResultSet rs = DataConnection.retrieveData("select * from MAYBAY");

        try {
            while (rs.next()){
                MayBay mayBay = new MayBay(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getInt(3)
                );
                list.add(mayBay);
            }
        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,"Lỗi loadData table MAYBAY",e);
        }

        return list;
    }

    public boolean add(MayBay mb) {
        //TODO: Tương tác với DB, thêm mayBay
        String sqlCommand = "INSERT INTO MAYBAY VALUE(?,?,?)";

        try {
            DataConnection.createStatement();
            PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

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

    public boolean updateMayBay(MayBay mb){
        String sqlCommand = "update MAYBAY set SOGHE= ?"+" where SHMB=?";

        try {
           DataConnection.createStatement();
            PreparedStatement ps=DataConnection.connection.prepareStatement(sqlCommand);

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

    public boolean deleteMayBay(String shmb){
        String sqlCommad = "DELETE FROM MAYBAY WHERE SHMB =?";
        try {
            DataConnection.createStatement();
            PreparedStatement ps=DataConnection.connection.prepareStatement(sqlCommad);
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
}
