package com.example.cuoiky.Model;

public class NongSan {
    private String maNS;
    private String tenNS;
    private String moTa;
    private int soLuong;
    private int donGia;
    private String image;

    @Override
    public String toString() {
        return "NongSan{" +
                "maNS='" + maNS + '\'' +
                ", tenNS='" + tenNS + '\'' +
                ", moTa='" + moTa + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public NongSan(String maNS, String tenNS, String moTa, int soLuong, int donGia, String image) {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.image = image;
    }
    public NongSan() {
        this.maNS = maNS;
        this.tenNS = tenNS;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.image = image;
    }
}
