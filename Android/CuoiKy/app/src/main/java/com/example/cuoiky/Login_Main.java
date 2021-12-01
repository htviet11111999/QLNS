package com.example.cuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_Main extends AppCompatActivity {
    ImageView img_qlnv, img_qlk, img_qln, img_qlx;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
                Intent intent = new Intent(Login_Main.this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        img_qlnv = (ImageView)findViewById(R.id.img_qlnv);
        img_qlk = (ImageView)findViewById(R.id.img_qlk);
        img_qln = (ImageView)findViewById(R.id.img_qln);
        img_qlx = (ImageView)findViewById(R.id.img_qlx);



        Intent intent = getIntent();
        String manv = intent.getStringExtra("manv");

        img_qlnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Main.this, NhanVienActivity.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });
        img_qlk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Main.this, NongSanActivity.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });

        img_qln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Main.this, NhapNSActivity.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });
        img_qlx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Main.this, XuatNSActivity.class);
                intent.putExtra("manv",manv);
                startActivity(intent);
            }
        });
    }
}
