package com.example.cuoiky.Model;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String diaChi;
    private int gioiTinh;

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh=" + gioiTinh +
                '}';
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public NhanVien(String maNV, String tenNV, String diaChi, int gioiTinh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }
    public NhanVien() {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }
}
