package com.example.studentmanagement.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.studentmanagement.R;
import com.example.studentmanagement.daos.ClassroomDAO;
import com.example.studentmanagement.model.Classroom;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {
    ListView lvClass;
    Button btnAddClass;
    ClassroomDAO classroomDAO;
    ArrayList<String> classList = new ArrayList();;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        findView();
        // get danh sach lop va hien thi
        getAllClass();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, classList);
        lvClass.setAdapter(adapter);

        // Intent đến màn thêm mới 1 lớp
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassActivity.this, AddClassActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getAllClass() {
        classroomDAO = new ClassroomDAO(this);
        for(Classroom classroom: classroomDAO.getAllClassroom()) {
            String label = "Mã lớp: "
                    + classroom.getId()
                    + "\nTên lớp: " + classroom.getName()
                    + "\nMô tả: " + classroom.getDescription();
            classList.add(label);
        }
    }

    private void findView() {
        lvClass = findViewById(R.id.lv_class);
        btnAddClass = findViewById(R.id.btn_add_class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh lại data
        classList.clear();
        getAllClass();
        adapter.notifyDataSetChanged();
    }
}