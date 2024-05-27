package com.example.quan_ly_tuyen_bay.Model;

import com.example.quan_ly_tuyen_bay.Connection.DataConnection;

import java.util.Date;

public class Ve {
    private String maChuyenBay;
    private String tenHanhKhach;
    private String sDT;
    private String maGhe;
    private int gia;
    private Date ngaysinh;
    private String email;
    private String cccd;

    public Ve(){

    }

    public Ve(String maChuyenBay, String tenHanhKhach, String sDT, String maGhe, int gia, Date ngaysinh, String email, String cccd) {
        this.maChuyenBay = maChuyenBay;
        this.tenHanhKhach = tenHanhKhach;
        this.sDT = sDT;
        this.maGhe = maGhe;
        this.gia = gia;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.cccd = cccd;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getTenHanhKhach() {
        return tenHanhKhach;
    }

    public void setTenHanhKhach(String tenHanhKhach) {
        this.tenHanhKhach = tenHanhKhach;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
