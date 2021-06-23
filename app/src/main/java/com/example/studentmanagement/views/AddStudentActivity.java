package com.example.studentmanagement.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.studentmanagement.R;
import com.example.studentmanagement.daos.StudentDAO;
import com.example.studentmanagement.model.Student;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {
    private static final String SCHOOL_YEAR[] = new String[]{"Năm nhất", "Năm hai", "Năm ba", "Năm bốn"};
    EditText edtHoTen, edtNamSinh, edtQueQuan;
    Spinner spnNamHoc;
    Button btnAdd;
    private static String namHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        findView();

        // init spinner view
        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SCHOOL_YEAR);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNamHoc.setAdapter(spinnerAdapter);

        // spinner event
        spnNamHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                namHoc = SCHOOL_YEAR[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                namHoc = SCHOOL_YEAR[0];
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtHoTen.getText().toString().trim();
                String namSinh = edtNamSinh.getText().toString().trim();
                String queQuan = edtQueQuan.getText().toString().trim();
                if(name.isEmpty()||namSinh.isEmpty()||queQuan.isEmpty()) {
                    Snackbar.make(btnAdd, "Vui lòng điền đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
                } else {
                    Student newStudent = new Student(name, queQuan, namHoc, Integer.parseInt(namSinh));
                    themMoiSinhVien(newStudent);
                }
            }
        });
    }

    private void themMoiSinhVien(Student newStudent) {
        StudentDAO studentDAO = new StudentDAO(this);
        boolean isSuccess = studentDAO.addNewStudent(newStudent);
        if(isSuccess) finish();
        else Snackbar.make(btnAdd, "Thêm sinh viên không thành công!", Snackbar.LENGTH_LONG).show();
    }

    private void findView() {
        edtHoTen = findViewById(R.id.edt_student_name);
        edtNamSinh = findViewById(R.id.edt_nam_sinh);
        edtQueQuan = findViewById(R.id.edt_address);
        spnNamHoc = findViewById(R.id.spn_nam_hoc);
        btnAdd = findViewById(R.id.btn_add_student);
    }
}