package com.example.cuoiky.Model;

public class CTNhapNS {
    private String maNS;
    private String tenNS;
    private String maN;
    private int soLuong;
    private int donGia;

    @Override
    public String toString() {
        return "CTNhapNS{" +
                "maNS='" + maNS + '\'' +
                ", tenNS='" + tenNS + '\'' +
                ", maN='" + maN + '\'' +
                ", soLuong=" + soLuong + '\'' +
                ", donGia=" + donGia +
                '}';
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getMaNS() {
        return maNS;
    }

    public void setMaNS(String maNS) {
        this.maNS = maNS;
    }

    public String getTenNS() {
        return tenNS;
    }

    public void setTenNS(String tenNS) {
        this.tenNS = tenNS;
    }

    public String getMaN() {
        return maN;
    }

    public void setMaN(String maN) {
        this.maN = maN;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public CTNhapNS(String maNS, String tenNS, String maN, int soLuong, int donGia) {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.maN = maN;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    public CTNhapNS() {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.maN = maN;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
}
