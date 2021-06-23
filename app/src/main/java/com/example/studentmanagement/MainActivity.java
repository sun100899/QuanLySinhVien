package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentmanagement.views.ClassActivity;
import com.example.studentmanagement.views.StudentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClass, btnStudent, btnCourseReg, btnStat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        btnClass.setOnClickListener(this);
        btnStudent.setOnClickListener(this);
        btnCourseReg.setOnClickListener(this);
        btnStat.setOnClickListener(this);
    }

    private void findView() {
        btnClass = findViewById(R.id.btn_class);
        btnStudent = findViewById(R.id.btn_student);
        btnCourseReg = findViewById(R.id.btn_dang_ky);
        btnStat = findViewById(R.id.btn_stat);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_class:
                intent = new Intent(MainActivity.this, ClassActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_student:
                intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent);
                break;
            default: break;
        }
    }
}