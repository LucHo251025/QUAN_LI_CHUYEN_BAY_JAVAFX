package com.example.quan_ly_tuyen_bay.Model;

public class NhanVien {
    private String sdt;
    private String tendn;
    private String tenNhanVien;
    private int luong;

    public NhanVien(String sdt, String tendn, String tenNhanVien, int luong) {
        this.sdt = sdt;
        this.tendn = tendn;
        this.tenNhanVien = tenNhanVien;
        this.luong = luong;
    }

    public  NhanVien(){

    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTendn() {
        return tendn;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }
}
