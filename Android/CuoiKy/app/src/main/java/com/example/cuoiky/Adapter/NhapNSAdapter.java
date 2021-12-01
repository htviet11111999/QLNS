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

public class NhapNSAdapter extends ArrayAdapter<NhapNS> {

    Context context;
    int layoutResouredId;
    ArrayList<NhapNS> data = null;

    public NhapNSAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<NhapNS> data) {
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
            convertView = layoutInflater.inflate(R.layout.listview_nhapns, null, true);
        }
        NhapNS nhapNS = getItem(position);
        TextView tvman =(TextView)convertView.findViewById(R.id.man);
        tvman.setText("Mã nhập nông sản: "+nhapNS.getMaN());
        TextView tvngay =(TextView)convertView.findViewById(R.id.ngayn);
        tvngay.setText("Ngày nhập: "+nhapNS.getNgay());
        TextView tvmanv =(TextView)convertView.findViewById(R.id.manvn);
        tvmanv.setText("Mã nhân viên: "+nhapNS.getMaNV());
        TextView tvtennv =(TextView)convertView.findViewById(R.id.tennvn);
        tvtennv.setText("Họ tên nhân viên: "+nhapNS.getTenNV());
        TextView tvtienthanhtoan =(TextView)convertView.findViewById(R.id.tienthanhtoan);
        tvtienthanhtoan.setText("Tiền thanh toán: "+nhapNS.getTienThanhToan());
        TextView trangthai =(TextView)convertView.findViewById(R.id.trangthai);
        if(nhapNS.getTrangThai()==0) trangthai.setText("Trạng thái: Chưa thanh toán");
        else trangthai.setText("Trạng thái: Đã thanh toán");

        return convertView;
    }
}