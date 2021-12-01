package com.example.cuoiky.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cuoiky.Api.ApiService;
import com.example.cuoiky.CTNhapNSActivity;
import com.example.cuoiky.Model.CTNhapNS;
import com.example.cuoiky.Model.CTXuatNS;
import com.example.cuoiky.Model.NhapNS;
import com.example.cuoiky.R;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CTXuatNSAdapter extends ArrayAdapter<CTXuatNS> {

    Context context;
    int layoutResouredId;
    ArrayList<CTXuatNS> data = null;

    public CTXuatNSAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<CTXuatNS> data) {
        super(context, layoutResouredId, data);
        this.context = context;
        this.layoutResouredId = layoutResouredId;
        this.data = data;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.listview_chitietxuatns, null, true);
        }
        CTXuatNS ctxuatNS = getItem(position);
        TextView tvmaNS =(TextView)convertView.findViewById(R.id.mansx);
        tvmaNS.setText("Mã nông sản: "+ ctxuatNS.getMaNS());
        TextView tvtenNS =(TextView)convertView.findViewById(R.id.tennsx);
        tvtenNS.setText("Tên nông sản: "+ctxuatNS.getTenNS());
        TextView tvmaX =(TextView)convertView.findViewById(R.id.maX);
        tvmaX.setText("Mã xuất nông sản: "+ctxuatNS.getMaX());
        TextView tvsoluong =(TextView)convertView.findViewById(R.id.soluongnsx);
        tvsoluong.setText("Số lượng: "+ctxuatNS.getSoLuong());
        Button btn_xoactx = (Button)convertView.findViewById(R.id.btn_xoactx);
        btn_xoactx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService.apiService.deleteCTXuatNS(ctxuatNS.getMaNS(),ctxuatNS.getMaX())
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.isSuccessful())
                                {
                                    Toast.makeText(context,"Deleted Successfully !",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context,"Call Api Error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        return convertView;
    }
}