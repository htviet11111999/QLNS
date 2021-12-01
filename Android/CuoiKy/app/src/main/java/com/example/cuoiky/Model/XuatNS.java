package com.example.cuoiky.Model;

import java.sql.Date;

public class XuatNS {
    private String maX;
    private String ngay;
    private String maNV;
    private String tenNV;

    @Override
    public String toString() {
        return "XuatNS{" +
                "maN='" + maX + '\'' +
                ", ngay='" + ngay + '\'' +
                ", maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                '}';
    }

    public String getMaX() {
        return maX;
    }

    public void setMaX(String maX) {
        this.maX = maX;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public XuatNS(String maX,String ngay, String maNV, String tenNV) {
        this.maX = maX;
        this.ngay = ngay;
        this.maNV = maNV;
        this.tenNV = tenNV;
    }
    public XuatNS() {
        this.maX = maX;
        this.ngay = ngay;
        this.maNV = maNV;
        this.tenNV = tenNV;
    }
}
