package com.example.cuoiky.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.cuoiky.Login_Main;
import com.example.cuoiky.Model.NhapNS;
import com.example.cuoiky.Model.XuatNS;
import com.example.cuoiky.NhapNSActivity;
import com.example.cuoiky.R;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XuatNSAdapter extends ArrayAdapter<XuatNS> {

    Context context;
    int layoutResouredId;
    ArrayList<XuatNS> data = null;

    public XuatNSAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<XuatNS> data) {
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
            convertView = layoutInflater.inflate(R.layout.listview_xuatns, null, true);
        }
        XuatNS xuatNS = getItem(position);
        TextView tvmax =(TextView)convertView.findViewById(R.id.max);
        tvmax.setText("Mã xuất nông sản: "+xuatNS.getMaX());
        TextView tvngay =(TextView)convertView.findViewById(R.id.ngayx);
        tvngay.setText("Ngày xuất: "+xuatNS.getNgay());
        TextView tvmanv =(TextView)convertView.findViewById(R.id.manvx);
        tvmanv.setText("Mã nhân viên: "+xuatNS.getMaNV());
        TextView tvtennv =(TextView)convertView.findViewById(R.id.tennvx);
        tvtennv.setText("Họ tên nhân viên: "+xuatNS.getTenNV());


        return convertView;
    }
}