package com.example.cuoiky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cuoiky.Adapter.XuatNSAdapter;
import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.Model.DangNhap;
import com.example.cuoiky.Model.XuatNS;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1 ;
    EditText  username;
    EditText  password;
    Button btn_login;

    ArrayList<XuatNS> data = new ArrayList<>();

    private  List<DangNhap> listDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btn_login);

        getListUser();
        getListXuat();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickLogin();
            }
        });
    }


    private void ClickLogin() {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (listDangNhap == null || listDangNhap.isEmpty()){
            return;
        }
        boolean isHaveUser = false;
        for(DangNhap dn : listDangNhap){
            if (user.equals(dn.getUserName()) && pass.equals(dn.getPassWord())){
                isHaveUser = true;
                break;
            }
        }
        if (isHaveUser){
            //vao login
            Toast.makeText(MainActivity.this,"Sign in successfully !",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Login_Main.class);
            intent.putExtra("manv",user);
            startActivity(intent);

        }else Toast.makeText(MainActivity.this,"Login failed, please check your account again !",Toast.LENGTH_SHORT).show();


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = Calendar.getInstance().getTime();
        String dateFormat = formatter.format(date);

        int dem =0;
        String text="";
        Log.e("ngay",dateFormat +" ");
        for(XuatNS xns : data){
            if (xns.getNgay().equals(dateFormat)){
                text += xns.getMaX()+"  ";
                dem = dem +1 ;
            }
        }
        Log.e("so luong don xuat ",text +" ");
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.thongbao);
        Notification notification = new NotificationCompat.Builder(this, thongbao.CHANNEL_ID)
                .setContentTitle("THÔNG BÁO XUẤT HÀNG")
                .setContentText("Bạn hiện có "+dem+" đơn xuất hàng voi ma xuat la :"+text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Bạn hiện có "+dem+" đơn xuất hàng voi ma xuat la :"+text))
                .setSmallIcon(R.drawable.carrot)
                .setLargeIcon(bitmap)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

    private  void getListUser(){
        ApiService.apiService.getListUser()
                .enqueue(new Callback<List<DangNhap>>() {
                    @Override
                    public void onResponse(Call<List<DangNhap>> call, Response<List<DangNhap>> response) {
                        listDangNhap = response.body();
                        Log.e("ListUser",listDangNhap.size() +" ");
                    }

                    @Override
                    public void onFailure(Call<List<DangNhap>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private  void getListXuat(){
        ApiService.apiService.getListXuatNongSan()
                .enqueue(new Callback<ArrayList<XuatNS>>() {
                    @Override
                    public void onResponse(Call<ArrayList<XuatNS>> call, Response<ArrayList<XuatNS>> response) {
                        data = response.body();
                        Log.e("ListXuatNS",data.size() +" ");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<XuatNS>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Call Api Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }


}