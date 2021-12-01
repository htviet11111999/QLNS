package com.example.cuoiky.Model;

public class CTXuatNS {
    private String maNS;
    private String tenNS;
    private String maX;
    private int soLuong;

    @Override
    public String toString() {
        return "CTNhapNS{" +
                "maNS='" + maNS + '\'' +
                ", tenNS='" + tenNS + '\'' +
                ", maN='" + maX + '\'' +
                ", soLuong=" + soLuong +
                '}';
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

    public String getMaX() {
        return maX;
    }

    public void setMaX(String maN) {
        this.maX = maN;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public CTXuatNS(String maNS, String tenNS, String maX, int soLuong) {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.maX = maX;
        this.soLuong = soLuong;
    }
    public CTXuatNS() {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.maX = maX;
        this.soLuong = soLuong;
    }
}
