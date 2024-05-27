package com.example.quan_ly_tuyen_bay.Server.Repository;

import com.example.quan_ly_tuyen_bay.Connection.LoadData;
import com.example.quan_ly_tuyen_bay.Model.Ve;
import com.example.quan_ly_tuyen_bay.Server.Database.DataConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeRepo {

    public List<Ve> findAllVe(String macb){

        List<Ve> veArrayList = new ArrayList<Ve>();
        ResultSet rs = DataConnection.retrieveData("select * from ve where MaCB like '"+ macb+"'");

        try {
            while (rs.next()){
                Ve ve = new Ve(
                        rs.getString(1).trim(),
                        rs.getString(2).trim(),
                        rs.getString(3).trim(),
                        rs.getString(4).trim(),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7).trim(),
                        rs.getString(8)
                );

                veArrayList.add(ve);
            }

        }catch (Exception e){
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE,null,e);
        }
        return veArrayList;
    }


    public boolean addVe(List<Ve> dsVe){
        String sqlCommand ="INSERT INTO VE VALUES(?,?,?,?,?,?,?,?)";

        try {
            for (Ve ve : dsVe){
                DataConnection.createStatement();
                PreparedStatement ps = DataConnection.connection.prepareStatement(sqlCommand);

                ps.setString(1,ve.getMaChuyenBay());
                ps.setString(2,ve.getTenHanhKhach());
                ps.setString(3,ve.getsDT());
                ps.setString(4,ve.getMaGhe());
                ps.setInt(5,ve.getGia());
                ps.setDate(6, (Date) ve.getNgaysinh());
                ps.setString(7,ve.getEmail());
                ps.setString(8,ve.getCccd());

                if(ps.executeUpdate() > 0){
                    System.out.println("Thêm vé thành công");
                    return  true;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteVe(String maCB,String MaGhe){
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
}
