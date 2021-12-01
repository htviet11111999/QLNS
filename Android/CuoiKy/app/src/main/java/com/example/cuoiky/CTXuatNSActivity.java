package com.example.cuoiky;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiky.Adapter.CTNhapNSAdapter;
import com.example.cuoiky.Adapter.CTXuatNSAdapter;
import com.example.cuoiky.Adapter.NhanVienAdapter;
import com.example.cuoiky.Adapter.NhapNSAdapter;
import com.example.cuoiky.Adapter.NongSanAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.CTNhapNS;
import com.example.cuoiky.Model.CTXuatNS;
import com.example.cuoiky.Model.DangNhap;
import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.Model.NhapNS;
import com.example.cuoiky.Model.NongSan;

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

public class CTXuatNSActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList <CTXuatNS> data = new ArrayList<>();
    ArrayList <String> dataNS ;
    ArrayList <NongSan> dataNongSan = new ArrayList<>();
    CTXuatNSAdapter adapter = null;
    Spinner spn_mans;
    TextView tv_tenns, tv_max;
    EditText edt_soluong;
    Button btn_them, btn_thoat,btn_load;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_chitietxuatns);

        lvDanhSach = (ListView)findViewById(R.id.lv_ctxuat);

        spn_mans = (Spinner) findViewById(R.id.spn_mansX);
        tv_tenns= (TextView) findViewById(R.id.tv_tennsX);
        tv_max= (TextView)findViewById(R.id.tv_maX);
        edt_soluong= (EditText)findViewById(R.id.edt_slX);

        btn_them = (Button)findViewById(R.id.btn_themctx);
        btn_thoat = (Button)findViewById(R.id.btn_thoatctx);
        btn_load = (Button)findViewById(R.id.btn_loadctx);

        Intent intent = getIntent();
        String maX = intent.getStringExtra("maX");
        String manv = intent.getStringExtra("manv");
        tv_max.setText(maX);

        ApiService.apiService.getListNongSan()
                .enqueue(new Callback<ArrayList<NongSan>>() {
                    @Override
                    public void onResponse(Call<ArrayList<NongSan>> call, Response<ArrayList<NongSan>> response) {
                        dataNongSan = response.body();
                        Log.e("ListNS",dataNongSan.size() +" ");
                        dataNS = new ArrayList<>(dataNongSan.size());

                        for(int i =0; i< dataNongSan.size(); i++){
                            dataNS.add(i,dataNongSan.get(i).getMaNS());
                        }
                        ArrayAdapter adapterNS = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, dataNS);
                        adapterNS.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        spn_mans.setAdapter(adapterNS);
                        spn_mans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                for(int i = 0; i< dataNongSan.size(); i++){
                                    if(spn_mans.getSelectedItem().equals(dataNongSan.get(position).getMaNS())){
                                        tv_tenns.setText(dataNongSan.get(position).getTenNS());
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    @Override
                    public void onFailure(Call<ArrayList<NongSan>> call, Throwable t) {
                        Toast.makeText(CTXuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });


        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.getListCTXuatns(maX)
                        .enqueue(new Callback<ArrayList<CTXuatNS>>() {
                            @Override
                            public void onResponse(Call<ArrayList<CTXuatNS>> call, Response<ArrayList<CTXuatNS>> response) {
                                data = response.body();
                                Log.e("ListCTXuatNS",data.size() +" ");
                                adapter = new CTXuatNSAdapter(getApplicationContext(), R.layout.listview_chitietxuatns, data);
                                lvDanhSach.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<ArrayList<CTXuatNS>> call, Throwable t) {
                                Toast.makeText(CTXuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CTXuatNS ctXuatNS = getCTXuatNS();
                ApiService.apiService.saveCTXuatNS(ctXuatNS)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean kq = response.body();
                                Toast.makeText(CTXuatNSActivity.this,"Added Successfully !",Toast.LENGTH_SHORT).show();
                                Log.e("ResponseCTXuat",kq +" ");
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(CTXuatNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CTXuatNSActivity.this, XuatNSActivity.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });
    }
    private CTXuatNS getCTXuatNS() {
       CTXuatNS ctxuatNS = new CTXuatNS();
        ctxuatNS.setMaNS(spn_mans.getSelectedItem().toString());
        ctxuatNS.setTenNS(tv_tenns.getText().toString().trim());
        ctxuatNS.setMaX(tv_max.getText().toString().trim());
        ctxuatNS.setSoLuong(Integer.parseInt(edt_soluong.getText().toString().trim()));
        return ctxuatNS;
    }



}
