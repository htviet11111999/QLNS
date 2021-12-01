package com.example.cuoiky.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    Context context;
    int layoutResouredId;
    ArrayList<NhanVien> data = null;

    public NhanVienAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<NhanVien> data) {
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
            convertView = layoutInflater.inflate(R.layout.listview_nhanvien, null, true);
        }
        NhanVien nhanVien = getItem(position);
        TextView tvmanv =(TextView)convertView.findViewById(R.id.manv);
        tvmanv.setText("Mã nhân viên: "+nhanVien.getMaNV());
        TextView tvtennv =(TextView)convertView.findViewById(R.id.tennv);
        tvtennv.setText("Tên nhân viên: "+nhanVien.getTenNV());
        TextView tvdiachi =(TextView)convertView.findViewById(R.id.diachi);
        tvdiachi.setText("Địa chỉ: "+nhanVien.getDiaChi());
        TextView tvgioitinh =(TextView)convertView.findViewById(R.id.gioitinh);
        if(nhanVien.getGioiTinh() == 0)
        tvgioitinh.setText("Giới tính: Nam");
        else tvgioitinh.setText("Giới tính: Nữ");

        return convertView;
    }
}