package com.example.studentmanagement.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentmanagement.R;
import com.example.studentmanagement.daos.StudentDAO;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {
    ListView lvStudent;
    Button btnAddStudent;
    StudentDAO studentDAO;
    ArrayList<String> studentList = new ArrayList();;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        findView();
        // get danh sach sinh vien va hien thi
        getAllStudent();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList);
        lvStudent.setAdapter(adapter);

        // Intent đến màn thêm mới 1 sinh viên
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAllStudent() {
        studentDAO = new StudentDAO(this);
        for(Student student: studentDAO.getAllStudent()) {
            String label = "Mã sinh viên: "
                    + student.getId()
                    + "\nHọ & tên: " + student.getHoTen()
                    + "\nNăm học: " + student.getNamhoc();
            studentList.add(label);
        }
    }

    private void findView() {
        lvStudent = findViewById(R.id.lv_student);
        btnAddStudent = findViewById(R.id.btn_add_student);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh lại data
        studentList.clear();
        getAllStudent();
        adapter.notifyDataSetChanged();
    }
}