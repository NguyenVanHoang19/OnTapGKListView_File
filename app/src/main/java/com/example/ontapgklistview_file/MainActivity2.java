package com.example.ontapgklistview_file;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView lvDanhSachSinhVien;
    private TextView tvThongTinSinhVien;
    private Button btnThoat;
    private List<SinhVien> sinhVienList = new ArrayList<SinhVien>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvDanhSachSinhVien = (ListView) findViewById(R.id.lvDanhSachSinhVien);
        tvThongTinSinhVien =(TextView) findViewById(R.id.tvThongTinSinhVien);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        String []listFile= fileList();
        for(int i=0;i<listFile.length;i++){
            docFile(listFile[i]);
        }
  //      List<String> sinhVien =  new ArrayList<>();
//        for(SinhVien sv : sinhVienList){
//            sinhVien.add(sv.getMaSinhVien() + "      " + sv.getTenSinhVien());
//        }
        ListViewAdapter listViewAdapter = new ListViewAdapter(this,R.layout.custom_listview,sinhVienList);
        lvDanhSachSinhVien.setAdapter(listViewAdapter);
     //   ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sinhVien);
      //  lvDanhSachSinhVien.setAdapter(arrayAdapter);

        lvDanhSachSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvThongTinSinhVien.setText("Mã sinh viên: "+sinhVienList.get(i).getMaSinhVien() + "  " +sinhVienList.get(i).getTenSinhVien());
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Xác nhận thoát?");
                builder.setMessage("Bạn có chắc chắn muốn thoát??");
                builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    public void docFile(String tenFile){
        try {
            FileInputStream inputStream = openFileInput(tenFile);
            int c;
            StringBuffer stringBuffer = new StringBuffer();
            while ((c = inputStream.read())!=-1){
                stringBuffer.append((char)c);
            }
       //     String data_UTF_8 = StandardCharsets.UTF_8.encode(data).toString();
            String[] sinhViens = stringBuffer.toString().split("\n");
            sinhVienList.add(new SinhVien(sinhViens[0],sinhViens[1]));
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}