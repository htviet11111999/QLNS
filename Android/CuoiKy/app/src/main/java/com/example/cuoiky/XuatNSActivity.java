package com.example.cuoiky;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiky.Adapter.NhanVienAdapter;
import com.example.cuoiky.Adapter.NhapNSAdapter;
import com.example.cuoiky.Adapter.XuatNSAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.DangNhap;
import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.Model.NhapNS;
import com.example.cuoiky.Model.XuatNS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XuatNSActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList <XuatNS> data = new ArrayList<>();
    ArrayList <NhanVien> dataNV = new ArrayList<>();
    private List<DangNhap> listDangNhap;
    XuatNSAdapter adapter = null;
    EditText edt_max, edt_ngay;
    TextView tv_manv, tv_tennv;
    Button btn_them, btn_thoat, btn_load;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlyxuat);

        lvDanhSach = (ListView)findViewById(R.id.lv_xuat);

        edt_max = (EditText)findViewById(R.id.edt_max);
        edt_ngay= (EditText)findViewById(R.id.edt_ngayxuat);
        tv_manv= (TextView)findViewById(R.id.tv_manvx);
        tv_tennv= (TextView)findViewById(R.id.tv_tenvnx);

        btn_them = (Button)findViewById(R.id.btn_themx);
        btn_thoat = (Button)findViewById(R.id.btn_thoatx);
        btn_load = (Button)findViewById(R.id.btn_loadx);

        edt_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

        getListUser();
        getListNhanVien();



        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.getListXuatNongSan()
                        .enqueue(new Callback<ArrayList<XuatNS>>() {
                            @Override
                            public void onResponse(Call<ArrayList<XuatNS>> call, Response<ArrayList<XuatNS>> response) {
                                data = response.body();
                                Log.e("ListXuatNS",data.size() +" ");
                                adapter = new XuatNSAdapter(getApplicationContext(), R.layout.listview_xuatns, data);
                                lvDanhSach.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<ArrayList<XuatNS>> call, Throwable t) {
                                Toast.makeText(XuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuatNS xuatNS = getXuatNS();
                ApiService.apiService.saveXuatNS(xuatNS)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean kq = response.body();
                                Toast.makeText(XuatNSActivity.this,"Added Successfully !",Toast.LENGTH_SHORT).show();
                                Log.e("ResponseXuatNS",kq +" ");
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(XuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        Intent intent = getIntent();
        String manv = intent.getStringExtra("manv");

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(XuatNSActivity.this, CTXuatNSActivity.class);
                intent.putExtra("maX",data.get(position).getMaX());
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ApiService.apiService.deleteXuatNS(data.get(position).getMaX())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(XuatNSActivity.this,"Deleted Successfully !",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(XuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                return false;
            }
        });
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XuatNSActivity.this, Login_Main.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });


    }
    private void ChonNgay() {
        Calendar cal = Calendar.getInstance();
        int ngay = cal.get(Calendar.DATE);
        int thang = cal.get(Calendar.MONTH);
        int nam = cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        cal.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edt_ngay.setText(simpleDateFormat.format(cal.getTime()));
                    }
                }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private XuatNS getXuatNS() {
        XuatNS xuatNS = new XuatNS();
        xuatNS.setMaX(edt_max.getText().toString().trim());
        xuatNS.setNgay(edt_ngay.getText().toString().trim());
        xuatNS.setMaNV(tv_manv.getText().toString().trim());
        xuatNS.setTenNV(tv_tennv.getText().toString().trim());
        return xuatNS;
    }
    private  void getListUser(){
        ApiService.apiService.getListUser()
                .enqueue(new Callback<List<DangNhap>>() {
                    @Override
                    public void onResponse(Call<List<DangNhap>> call, Response<List<DangNhap>> response) {
                        listDangNhap = response.body();
                        Log.e("ListUser",listDangNhap.size() +" ");
                        Intent intent = getIntent();
                        String manv = intent.getStringExtra("manv");
                        for(int i =0; i< listDangNhap.size(); i++){
                            if (manv.equals(listDangNhap.get(i).getUserName())){
                                tv_manv.setText(listDangNhap.get(i).getMaNV());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<DangNhap>> call, Throwable t) {
                        Toast.makeText(XuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private  void getListNhanVien(){
        ApiService.apiService.getListNhanVien()
                .enqueue(new Callback<ArrayList<NhanVien>>() {
                    @Override
                    public void onResponse(Call<ArrayList<NhanVien>> call, Response<ArrayList<NhanVien>> response) {
                        dataNV = response.body();
                        Log.e("ListNV",dataNV.size() +" ");
                        for(int i =0; i< dataNV.size(); i++){
                            if (tv_manv.getText().toString().equals(dataNV.get(i).getMaNV())){
                                tv_tennv.setText(dataNV.get(i).getTenNV());
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<NhanVien>> call, Throwable t) {
                        Toast.makeText(XuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
