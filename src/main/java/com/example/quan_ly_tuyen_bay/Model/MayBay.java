package com.example.quan_ly_tuyen_bay.Model;

import java.io.Serializable;

public class MayBay implements Serializable
{
    private String SHMB;
    private String hangBay;
    private int soGhe;

    public  MayBay() {

    }

    public MayBay(String SHMB, String hangBay, int soGhe) {
        this.SHMB = SHMB;
        this.hangBay = hangBay;
        this.soGhe = soGhe;
    }

    public String getSHMB() {
        return SHMB;
    }

    public void setSHMB(String SHMB) {
        this.SHMB = SHMB;
    }

    public String getHangBay() {
        return hangBay;
    }

    public void setHangBay(String hangBay) {
        this.hangBay = hangBay;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    @Override
    public String toString() {
        return "MayBay{" +
                "SHMB='" + SHMB + '\'' +
                ", hangBay='" + hangBay + '\'' +
                ", soGhe=" + soGhe +
                '}';
    }
}
