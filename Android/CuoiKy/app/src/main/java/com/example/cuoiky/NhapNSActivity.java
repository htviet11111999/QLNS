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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiky.Adapter.NhanVienAdapter;
import com.example.cuoiky.Adapter.NhapNSAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.DangNhap;
import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.Model.NhapNS;

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

public class NhapNSActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList <NhapNS> data = new ArrayList<>();
    ArrayList <NhanVien> dataNV = new ArrayList<>();
    private List<DangNhap> listDangNhap;
    NhapNSAdapter adapter = null;
    EditText edt_man, edt_ngay,edt_tenkh, edt_sdt, edt_diachi;
    TextView tv_manv, tv_tennv;
    Button btn_them, btn_thoat, btn_load;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlynhap);

        lvDanhSach = (ListView)findViewById(R.id.lv_nhap);

        edt_man = (EditText)findViewById(R.id.edt_man);
        edt_ngay= (EditText)findViewById(R.id.edt_ngaynhap);
        edt_tenkh= (EditText)findViewById(R.id.edt_tenkh);
        edt_sdt= (EditText)findViewById(R.id.edt_sdt);
        edt_diachi= (EditText)findViewById(R.id.edt_diachi);
        tv_manv= (TextView)findViewById(R.id.tv_manvn);
        tv_tennv= (TextView)findViewById(R.id.tv_tenvnn);

        btn_them = (Button)findViewById(R.id.btn_themn);
        btn_thoat = (Button)findViewById(R.id.btn_thoatn);
        btn_load = (Button)findViewById(R.id.btn_loadn);

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
                ApiService.apiService.getListNhapNongSan()
                        .enqueue(new Callback<ArrayList<NhapNS>>() {
                            @Override
                            public void onResponse(Call<ArrayList<NhapNS>> call, Response<ArrayList<NhapNS>> response) {
                                data = response.body();
                                Log.e("ListNhapNS",data.size() +" ");
                                adapter = new NhapNSAdapter(getApplicationContext(), R.layout.listview_nhapns, data);
                                lvDanhSach.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<ArrayList<NhapNS>> call, Throwable t) {
                                Toast.makeText(NhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhapNS nhapNS = getNhapNS();
                ApiService.apiService.saveNhapNS(nhapNS)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean kq = response.body();
                                Toast.makeText(NhapNSActivity.this,"Added Successfully !",Toast.LENGTH_SHORT).show();
                                Log.e("ResponseNhapNS",kq +" ");
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(NhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        Intent intent = getIntent();
        String manv = intent.getStringExtra("manv");

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NhapNSActivity.this, CTNhapNSActivity.class);
                intent.putExtra("maN",data.get(position).getMaN());
                intent.putExtra("manv",manv);
                intent.putExtra("sodt",data.get(position).getSoDT());
                intent.putExtra("ngay",data.get(position).getNgay());
                startActivity(intent);
            }
        });
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ApiService.apiService.deleteNhapNS(data.get(position).getMaN())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(NhapNSActivity.this,"Deleted Successfully !",Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(NhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                return false;
            }
        });
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhapNSActivity.this, Login_Main.class);
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

    private NhapNS getNhapNS() {
        NhapNS nhapNS = new NhapNS();
        nhapNS.setMaN(edt_man.getText().toString().trim());
        nhapNS.setTenKH(edt_tenkh.getText().toString().trim());
        nhapNS.setSoDT(edt_sdt.getText().toString().trim());
        nhapNS.setDiaChi(edt_diachi.getText().toString().trim());
        nhapNS.setNgay(edt_ngay.getText().toString().trim());
        nhapNS.setMaNV(tv_manv.getText().toString().trim());
        nhapNS.setTenNV(tv_tennv.getText().toString().trim());
        nhapNS.setTienThanhToan(0);
        nhapNS.setTrangThai(0);
        return nhapNS;
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
                        Toast.makeText(NhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(NhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
