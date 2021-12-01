package com.example.cuoiky.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cuoiky.Model.NhanVien;
import com.example.cuoiky.Model.NongSan;
import com.example.cuoiky.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class NongSanAdapter extends ArrayAdapter<NongSan> {

    Context context;
    int layoutResouredId;
    ArrayList<NongSan> data = null;

    public NongSanAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<NongSan> data) {
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
            convertView = layoutInflater.inflate(R.layout.listview_nongsan, null, true);
        }
        NongSan nongSan = getItem(position);
        TextView tvmans =(TextView)convertView.findViewById(R.id.mans);
        tvmans.setText("Mã nông sản: "+nongSan.getMaNS());
        TextView tvtenns =(TextView)convertView.findViewById(R.id.tenns);
        tvtenns.setText("Tên nông sản: "+nongSan.getTenNS());
        TextView tvmota =(TextView)convertView.findViewById(R.id.mota);
        tvmota.setText("Mô tả: "+nongSan.getMoTa());
        TextView tvsoluong =(TextView)convertView.findViewById(R.id.soluong);
        tvsoluong.setText("Số lượng: "+nongSan.getSoLuong());
        TextView tvdongia =(TextView)convertView.findViewById(R.id.dongia);
        tvdongia.setText("Đơn giá: "+nongSan.getDonGia());
        String photoImage = nongSan.getImage();
        photoImage = photoImage.substring(photoImage.indexOf(",") + 1);
        byte[] outImage = Base64.decode(photoImage.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        ImageView imgns =(ImageView)convertView.findViewById(R.id.hinhns);
        imgns.setImageBitmap(theImage);
        return convertView;
    }
}