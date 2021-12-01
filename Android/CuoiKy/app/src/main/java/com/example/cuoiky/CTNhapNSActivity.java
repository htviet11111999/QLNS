package com.example.cuoiky;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cuoiky.Adapter.CTNhapNSAdapter;
import com.example.cuoiky.Adapter.NhanVienAdapter;
import com.example.cuoiky.Adapter.NhapNSAdapter;
import com.example.cuoiky.Adapter.NongSanAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.CTNhapNS;
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

public class CTNhapNSActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;
    private static final String LOG_TAG = "AndroidExample";
    private ListView lvDanhSach;
    ArrayList <CTNhapNS> data = new ArrayList<>();
    ArrayList <String> dataNS ;
    ArrayList <NongSan> dataNongSan = new ArrayList<>();
    CTNhapNSAdapter adapter = null;
    Spinner spn_mans;
    TextView tv_tenns, tv_man, tv_dongiactn;
    EditText edt_soluong;
    Button btn_them, btn_thoat,btn_load,btn_thanhtoan;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_chitietnhapns);

        lvDanhSach = (ListView)findViewById(R.id.lv_ctnhap);

        spn_mans = (Spinner) findViewById(R.id.spn_mansN);
        tv_tenns= (TextView) findViewById(R.id.tv_tennsN);
        tv_dongiactn= (TextView) findViewById(R.id.tv_dongiactn);
        tv_man= (TextView)findViewById(R.id.tv_maN);
        edt_soluong= (EditText)findViewById(R.id.edt_slN);

        btn_them = (Button)findViewById(R.id.btn_themctn);
        btn_thoat = (Button)findViewById(R.id.btn_thoatctn);
        btn_load = (Button)findViewById(R.id.btn_loadctn);
        btn_thanhtoan = (Button)findViewById(R.id.btn_thanhtoanctn);

        Intent intent = getIntent();
        String maN = intent.getStringExtra("maN");
        String manv = intent.getStringExtra("manv");
        String sodt= intent.getStringExtra("sodt");
        String ngay= intent.getStringExtra("ngay");

        tv_man.setText(maN);

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
                                        tv_dongiactn.setText(String.valueOf(dataNongSan.get(position).getDonGia()));
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
                        Toast.makeText(CTNhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });

        btn_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Mời bạn đến cửa hàng Carrot để thanh toán hóa đơn nhập hàng "+maN+" trong ngày "+ngay+". Chúng tôi xin cảm ơn !";
                sendMSM(sodt,message);
                Log.e("Nội dung",message +" ");
                Log.e("Số đt",sodt +" ");
                ApiService.apiService.updateNhapNS(maN)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean>call, Response<Boolean>response) {
                                Boolean kq = response.body();
                                Log.e("ResponseNS",kq +" ");


                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });

            }
        });


        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.getListCTNhapns(maN)
                        .enqueue(new Callback<ArrayList<CTNhapNS>>() {
                            @Override
                            public void onResponse(Call<ArrayList<CTNhapNS>> call, Response<ArrayList<CTNhapNS>> response) {
                                data = response.body();
                                Log.e("ListCTNhapNS",data.size() +" ");
                                adapter = new CTNhapNSAdapter(getApplicationContext(), R.layout.listview_chitietnhapns, data);
                                lvDanhSach.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<ArrayList<CTNhapNS>> call, Throwable t) {
                                Toast.makeText(CTNhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CTNhapNS ctNhapNS = getCTNhapNS();
                ApiService.apiService.saveCTNHapNS(ctNhapNS)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean kq = response.body();
                                Toast.makeText(CTNhapNSActivity.this,"Added Successfully !",Toast.LENGTH_SHORT).show();
                                Log.e("ResponseCTNhap",kq +" ");
                            }
                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(CTNhapNSActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CTNhapNSActivity.this, NhapNSActivity.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });
    }





    private CTNhapNS getCTNhapNS() {
        CTNhapNS ctnhapNS = new CTNhapNS();
        ctnhapNS.setMaNS(spn_mans.getSelectedItem().toString());
        ctnhapNS.setTenNS(tv_tenns.getText().toString().trim());
        ctnhapNS.setDonGia(Integer.parseInt(tv_dongiactn.getText().toString().trim()));
        ctnhapNS.setSoLuong(Integer.parseInt(edt_soluong.getText().toString().trim()));
        ctnhapNS.setMaN(tv_man.getText().toString().trim());

        return ctnhapNS;
    }




    private void sendMSM(String phoneNumber, String mess)  {
        Uri uri = Uri.parse("smsto: " +phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);

        intent.putExtra("sms_body",mess);
        try{
            startActivity(intent);
        }catch (Exception e){
            Log.e(LOG_TAG,"Your sms has failed..."+ e.getMessage(),e);
            Toast.makeText(CTNhapNSActivity.this,"Your sms has failed..."+e.getMessage(),
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    }






