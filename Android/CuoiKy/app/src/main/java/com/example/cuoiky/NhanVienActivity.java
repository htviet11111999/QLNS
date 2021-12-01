package com.example.cuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiky.Adapter.NhanVienAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.NhanVien;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhanVienActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList <NhanVien> data = new ArrayList<>();
    NhanVienAdapter adapter = null;
    Button btn_them, btn_sua, btn_thoat, btn_xoa, btn_load;
    EditText txtmanv,txttennv, txtdiachi;
    RadioButton radio_nam, radio_nu;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlynhanvien);

        lvDanhSach = (ListView)findViewById(R.id.lv_nhanvien);

        txtmanv = (EditText)findViewById(R.id.edt_manv);
        txttennv = (EditText)findViewById(R.id.edt_tennv);
        txtdiachi = (EditText)findViewById(R.id.edt_diachi);
        radio_nam = (RadioButton)findViewById(R.id.radio_nam);
        radio_nu = (RadioButton)findViewById(R.id.radio_nu);

        btn_them = (Button)findViewById(R.id.btn_them);
        btn_sua = (Button)findViewById(R.id.btn_sua);
        btn_xoa = (Button)findViewById(R.id.btn_xoa);
        btn_thoat = (Button)findViewById(R.id.btn_thoat);
        btn_load = (Button)findViewById(R.id.btn_load);

        Intent intent = getIntent();
        String manv = intent.getStringExtra("manv");

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.getListNhanVien()
                        .enqueue(new Callback<ArrayList<NhanVien>>() {
                            @Override
                            public void onResponse(Call<ArrayList<NhanVien>> call, Response<ArrayList<NhanVien>> response) {
                                data = response.body();
                                Log.e("ListNhanVien",data.size() +" ");
                                adapter = new NhanVienAdapter(getApplicationContext(), R.layout.listview_nhanvien, data);
                                lvDanhSach.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<ArrayList<NhanVien>> call, Throwable t) {
                                Toast.makeText(NhanVienActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = getNhanVien();
                ApiService.apiService.saveNhanVien(nhanVien)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean kq = response.body();
                                Toast.makeText(NhanVienActivity.this,"Added Successfully !",Toast.LENGTH_SHORT).show();
                                Log.e("Response",kq +" ");
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(NhanVienActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.deleteNhanVien(txtmanv.getText().toString().trim())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(NhanVienActivity.this,"Deleted Successfully !",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(NhanVienActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = getNhanVien();
                ApiService.apiService.updateNhanVien(txtmanv.getText().toString().trim(),nhanVien)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean>call, Response<Boolean>response) {
                                Boolean kq = response.body();
                                Log.e("Response",kq +" ");
                                if(kq == false)
                                    Toast.makeText(NhanVienActivity.this,"Cannot edit ID !",Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(NhanVienActivity.this,"Updated Successfully !",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(NhanVienActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this, Login_Main.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nv = data.get(position);
                txtmanv.setText(nv.getMaNV());
                txttennv.setText(nv.getTenNV());
                txtdiachi.setText(nv.getDiaChi());
                if(nv.getGioiTinh() == 0) radio_nam.setChecked(true);
                else radio_nu.setChecked(true);
            }
        });
    }
    private NhanVien getNhanVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(txtmanv.getText().toString().trim());
        nhanVien.setTenNV(txttennv.getText().toString().trim());
        nhanVien.setDiaChi(txtdiachi.getText().toString().trim());
        if(radio_nam.isChecked()) nhanVien.setGioiTinh(0);
        else nhanVien.setGioiTinh(1);
        return nhanVien;
    }
}
