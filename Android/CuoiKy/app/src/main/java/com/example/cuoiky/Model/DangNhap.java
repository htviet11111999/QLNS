package com.example.cuoiky.Model;

public class DangNhap {
    private String maNV;
    private String userName;
    private int rule;
    private String passWord;

    @Override
    public String toString() {
        return "DangNhap{" +
                "maNV='" + maNV + '\'' +
                ", userName='" + userName + '\'' +
                ", rule='" + rule + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public DangNhap(String maNV, String userName,int rule, String passWord) {
        this.maNV = maNV;
        this.userName = userName;
        this.rule = rule;
        this.passWord = passWord;
    }
    public DangNhap() {
        this.maNV = maNV;
        this.userName = userName;
        this.rule = rule;
        this.passWord = passWord;
    }
    public DangNhap(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
