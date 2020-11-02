package com.example.ontapgklistview_file;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    int idLayOut;
    List<SinhVien> sinhVienList = new ArrayList<SinhVien>();

    public ListViewAdapter(Context context, int idLayOut, List<SinhVien> sinhVienList) {
        this.context = context;
        this.idLayOut = idLayOut;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayOut,viewGroup,false);
        TextView tvMaSinhVien = (TextView) view.findViewById(R.id.tvMaSinhVien_lv);
        TextView tvTenSinhVien = (TextView) view.findViewById(R.id.tvTenSinhVien_lv);
        tvMaSinhVien.setText(sinhVienList.get(i).getMaSinhVien());
        tvTenSinhVien.setText(sinhVienList.get(i).getTenSinhVien());
        return view;
    }
}
