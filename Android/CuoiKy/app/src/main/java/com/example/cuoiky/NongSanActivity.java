package com.example.cuoiky;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cuoiky.Adapter.NongSanAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.Model.NongSan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NongSanActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList <NongSan> data = new ArrayList<>();
    NongSanAdapter adapter = null;
    Button btn_them, btn_sua, btn_thoat, btn_xoa, btn_load;
    EditText txtmans,txttenns, txtmota, txtsoluong, txtdongia;
    ImageView hinhns, upload;
    int REQUEST_CODE_FOLDER = 456;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlynongsan);

        lvDanhSach = (ListView)findViewById(R.id.lv_nongsan);

        txtmans = (EditText)findViewById(R.id.edt_mans);
        txttenns = (EditText)findViewById(R.id.edt_tenns);
        txtmota = (EditText)findViewById(R.id.edt_mota);
        txtdongia = (EditText)findViewById(R.id.edt_dongia);
        txtsoluong = (EditText)findViewById(R.id.edt_soluong);

        btn_them = (Button)findViewById(R.id.btn_themns);
        btn_sua = (Button)findViewById(R.id.btn_suans);
        btn_xoa = (Button)findViewById(R.id.btn_xoans);
        btn_thoat = (Button)findViewById(R.id.btn_thoatns);
        btn_load = (Button)findViewById(R.id.btn_loadns);

        hinhns =(ImageView)findViewById(R.id.hinhnsmain);
        upload =(ImageView)findViewById(R.id.imgUploadns);

        Intent intent = getIntent();
        String manv = intent.getStringExtra("manv");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });


        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.getListNongSan()
                        .enqueue(new Callback<ArrayList<NongSan>>() {
                            @Override
                            public void onResponse(Call<ArrayList<NongSan>> call, Response<ArrayList<NongSan>> response) {
                                data = response.body();
                                Log.e("ListNongSan",data.size() +" ");
                                adapter = new NongSanAdapter(getApplicationContext(), R.layout.listview_nongsan, data);
                                lvDanhSach.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<ArrayList<NongSan>> call, Throwable t) {
                                Toast.makeText(NongSanActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 NongSan nongSan = getNongSan();
                ApiService.apiService.saveNongSan(nongSan)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                Boolean kq = response.body();
                                Toast.makeText(NongSanActivity.this,"Added Successfully !",Toast.LENGTH_SHORT).show();
                                Log.e("ResponseNS",kq +" ");
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(NongSanActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.deleteNongSan(txtmans.getText().toString().trim())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(NongSanActivity.this,"Deleted Successfully !",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(NongSanActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NongSan nongSan = getNongSan();
                ApiService.apiService.updateNongSan(txtmans.getText().toString().trim(),nongSan)
                        .enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean>call, Response<Boolean>response) {
                                Boolean kq = response.body();
                                Log.e("ResponseNS",kq +" ");
                                if(kq == false)
                                    Toast.makeText(NongSanActivity.this,"Cannot edit ID !",Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(NongSanActivity.this,"Updated Successfully !",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(NongSanActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NongSanActivity.this, Login_Main.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NongSan nongSan = data.get(position);
                txtmans.setText(nongSan.getMaNS());
                txttenns.setText(nongSan.getTenNS());
                txtmota.setText(nongSan.getMoTa());
                txtdongia.setText(String.valueOf(nongSan.getDonGia()));
                txtsoluong.setText(String.valueOf(nongSan.getSoLuong()));
                String photoImage = nongSan.getImage();
                photoImage = photoImage.substring(photoImage.indexOf(",") + 1);
                byte[] outImage = Base64.decode(photoImage.getBytes(), Base64.DEFAULT);
                ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                hinhns.setImageBitmap(theImage);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                hinhns.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private NongSan getNongSan() {
        NongSan nongSan = new NongSan();
        nongSan.setMaNS(txtmans.getText().toString().trim());
        nongSan.setTenNS(txttenns.getText().toString().trim());
        nongSan.setMoTa(txtmota.getText().toString().trim());
        nongSan.setDonGia(Integer.parseInt(txtdongia.getText().toString().trim()));
        nongSan.setSoLuong(Integer.parseInt(txtsoluong.getText().toString().trim()));
        Bitmap bitmap = ((BitmapDrawable)hinhns.getDrawable()).getBitmap();
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bas);
        byte[] imBytes=bas.toByteArray();
        String encodedImage = Base64.encodeToString(imBytes, Base64.DEFAULT);
        String result = "data:image/png;base64,"+encodedImage;
        nongSan.setImage(result);
        return nongSan;
    }
}
