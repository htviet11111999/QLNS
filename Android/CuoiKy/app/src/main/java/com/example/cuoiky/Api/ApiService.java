package com.example.cuoiky.Api;

import com.example.cuoiky.Model.CTNhapNS;
import com.example.cuoiky.Model.CTXuatNS;
import com.example.cuoiky.Model.DangNhap;
import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.Model.NhapNS;
import com.example.cuoiky.Model.NongSan;
import com.example.cuoiky.Model.XuatNS;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //Đăng nhập
    @GET("dangnhap")
    Call<List<DangNhap>> getListUser();
    //Các hàm xử lý nhân viên
    @GET("nhanvien")
    Call<ArrayList<NhanVien>> getListNhanVien();
    @POST("nhanvien")
    Call<Boolean> saveNhanVien (
    @Body NhanVien nhanVien
    );
    @DELETE("nhanvien/{maNV}")
    Call<Void> deleteNhanVien (@Path("maNV") String maNV);
    @PUT("nhanvien/{maNV}")
    Call<Boolean> updateNhanVien(@Path("maNV") String maNV, @Body NhanVien nhanVien);
    //Các hàm xử lý nông sản
    @GET("nongsan")
    Call<ArrayList<NongSan>> getListNongSan();
    @POST("nongsan")
    Call<Boolean> saveNongSan (
            @Body NongSan nongSan
    );
    @DELETE("nongsan/{maNS}")
    Call<Void> deleteNongSan (@Path("maNS") String maNS);
    @PUT("nongsan/{maNS}")
    Call<Boolean> updateNongSan(@Path("maNS") String maNS, @Body NongSan nongSan);
    //Các hàm xử lý nhập nông sản
    @GET("nhapnongsan")
    Call<ArrayList<NhapNS>> getListNhapNongSan();
    @POST("nhapnongsan")
    Call<Boolean> saveNhapNS (
            @Body NhapNS nhapNS
    );
    @PUT("nhapnongsan/{maN}")
    Call<Boolean> updateNhapNS(@Path("maN") String maN);
    @DELETE("nhapnongsan/{maN}")
    Call<Void> deleteNhapNS (@Path("maN") String maN);
    //Các hàm xử lý chi tiết nhập nông sản
    @GET("ctn/{maN}")
    Call<ArrayList<CTNhapNS>> getListCTNhapns(@Path("maN") String maN);
    @POST("ctn")
    Call<Boolean> saveCTNHapNS (
            @Body CTNhapNS ctNhapNS
    );
    @DELETE("ctn/{maNS}/{maN}")
    Call<Void> deleteCTNhapNS (@Path("maNS") String maNS, @Path("maN") String maN);
    //Các hàm xử lý xuất nông sản
    @GET("xuatnongsan")
    Call<ArrayList<XuatNS>> getListXuatNongSan();
    @POST("xuatnongsan")
    Call<Boolean> saveXuatNS (
            @Body XuatNS xuatNS
    );
    @DELETE("xuatnongsan/{maX}")
    Call<Void> deleteXuatNS (@Path("maX") String maX);

    //Các hàm xử lý chi tiết xuất nông sản
    @GET("ctx/{maX}")
    Call<ArrayList<CTXuatNS>> getListCTXuatns(@Path("maX") String maX);
    @POST("ctx")
    Call<Boolean> saveCTXuatNS (
            @Body CTXuatNS ctxuatNS
    );
    @DELETE("ctx/{maNS}/{maX}")
    Call<Void> deleteCTXuatNS (@Path("maNS") String maNS, @Path("maX") String maX);
}
