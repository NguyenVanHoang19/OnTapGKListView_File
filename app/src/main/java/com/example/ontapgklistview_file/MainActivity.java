package com.example.ontapgklistview_file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btnLuuSinhVien,btnXoaRong,btnXemDanhSach;
    private EditText edtMaSinhVien,edtTenSinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLuuSinhVien = (Button) findViewById(R.id.btnLuuSinhVien);
        btnXoaRong = (Button) findViewById(R.id.btnXoaRong);
        btnXemDanhSach = (Button) findViewById(R.id.btnXemDanhSach);
        edtMaSinhVien = (EditText) findViewById(R.id.maSinhVien);
        edtTenSinhVien =(EditText) findViewById(R.id.tenSinhVien);
        btnLuuSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               luuFile(edtMaSinhVien.getText().toString());
           //     deleteFile(edtMaSinhVien.getText().toString());
            }
        });
        btnXoaRong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtMaSinhVien.setText("");
                edtTenSinhVien.setText("");
            }
        });
        btnXemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    public void luuFile(String tenFile){
        try {
            String[] fileList = fileList();
            for(int i=0;i<fileList.length;i++){
                if(fileList[i].toString().equals(tenFile)){
                    Toast.makeText(getApplicationContext(),"Trùng mã sinh viên",Toast.LENGTH_LONG).show();
                    return;
                }
            }
            FileOutputStream inputStream = openFileOutput(tenFile, Context.MODE_PRIVATE);
            inputStream.write((edtMaSinhVien.getText().toString()+"\n"+edtTenSinhVien.getText().toString()).getBytes());
            inputStream.close();
            Toast.makeText(getApplicationContext(),"Luu thanh cong!",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}