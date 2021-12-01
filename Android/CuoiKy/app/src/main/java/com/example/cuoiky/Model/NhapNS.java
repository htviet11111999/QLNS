package com.example.cuoiky.Model;

import java.sql.Date;

public class NhapNS {
    private String maN;
    private String tenKH;
    private String soDT;
    private String diaChi;
    private String ngay;
    private String maNV;
    private String tenNV;
    private int tienThanhToan;
    private int trangThai;

    public String getMaN() {
        return maN;
    }

    public void setMaN(String maN) {
        this.maN = maN;
    }

    @Override
    public String toString() {
        return "NhapNS{" +
                "maN='" + maN + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", soDT=" + soDT +
                ", diaChi='" + diaChi + '\'' +
                ", ngay='" + ngay + '\'' +
                ", maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", tienThanhToan=" + tienThanhToan +
                ", trangThai=" + trangThai +
                '}';
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public int getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(int tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public NhapNS(String maN, String tenKH, String soDT, String diaChi, String ngay, String maNV, String tenNV, int tienThanhToan, int trangThai) {
        this.maN = maN;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.ngay = ngay;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tienThanhToan = tienThanhToan;
        this.trangThai = trangThai;
    }

    public NhapNS() {
        this.maN = maN;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.ngay = ngay;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.tienThanhToan = tienThanhToan;
        this.trangThai = trangThai;
    }
}
