package com.example.quan_ly_tuyen_bay.Model;

public class NhanVien {
    String sdt;
    String tendn;
    String tenKhachHang;

    public NhanVien(){

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

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public NhanVien(String sdt, String tendn, String tenKhachHang) {
        this.sdt = sdt;
        this.tendn = tendn;
        this.tenKhachHang = tenKhachHang;
    }
}
