package com.example.quan_ly_tuyen_bay.Model;


import com.example.quan_ly_tuyen_bay.Controller.Controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class ChuyenBay {
    public static final int CONVE = 0;
    public static final int HETVE = 1;
    public static final int HUYCHUYEN = 2;
    public static final int HOANTAT = 3;

    private String maChuyenBay;
    private String SHMB;
    private DuongBay duongBay;
    private Date ngayBay;
    private LocalTime gioBay;
    private int trangThai;
    private ArrayList<Ve> veArrayList = new ArrayList<Ve>();

    public void ChuyenBay() {

    }

    public ChuyenBay(String maChuyenBay, String SHMB, DuongBay duongBay, Date ngayBay, LocalTime gioBay, int trangThai) {
        this.maChuyenBay = maChuyenBay;
        this.SHMB = SHMB;
        this.duongBay = duongBay;
        this.ngayBay = ngayBay;
        this.gioBay = gioBay;
        this.trangThai = trangThai;
    }




    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getSHMB() {
        return SHMB;
    }

    public void setSHMB(String SHMB) {
        this.SHMB = SHMB;
    }

    public DuongBay getDuongBay() {
        return duongBay;
    }

    public void setDuongBay(DuongBay duongBay) {
        this.duongBay = duongBay;
    }

    public Date getNgayBay() {
        return ngayBay;
    }

    public void setNgayBay(Date ngayBay) {
        this.ngayBay = ngayBay;
    }

    public LocalTime getGioBay() {
        return gioBay;
    }

    public void setGioBay(LocalTime gioBay) {
        this.gioBay = gioBay;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public ArrayList<Ve> getVeArrayList() {
        return veArrayList;
    }

    public void setVeArrayList(ArrayList<Ve> veArrayList) {
        this.veArrayList = veArrayList;
    }

    public static DuongBay timDuongBay(String maDuongBay) {
        for (DuongBay db : Controller.duongBayArrayList) {
            if (db.getMaDuongBay().equals(maDuongBay)) {
                return db;
            }
        }
        return null;
    }

    public int getSoGhe() {
        for (MayBay mb : Controller.mayBayArrayList) {
            if (mb.getSHMB().equals(this.SHMB)) {
                return mb.getSoGhe();
            }
        }
        return -1;
    }

    public int getSoGheTrong() {
        return getSoGhe() - veArrayList.size();
    }


    public Date getCBTime() {
        return new Date(this.ngayBay.getYear(),this.ngayBay.getMonth(),this.ngayBay.getDay(),this.gioBay.getHour(),this.gioBay.getMinute());
    }

    @Override
    public String toString() {
        return "ChuyenBay{" +
                "maChuyenBay='" + maChuyenBay + '\'' +
                ", SHMB='" + SHMB + '\'' +
                ", duongBay=" + duongBay +
                ", ngayBay=" + ngayBay +
                ", gioBay=" + gioBay +
                ", trangThai=" + trangThai +
                ", veArrayList=" + veArrayList +
                '}';
    }
}
