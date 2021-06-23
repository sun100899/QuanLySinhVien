package com.example.studentmanagement.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.studentmanagement.R;
import com.example.studentmanagement.daos.ClassroomDAO;
import com.example.studentmanagement.model.Classroom;
import com.google.android.material.snackbar.Snackbar;

public class AddClassActivity extends AppCompatActivity {
    EditText edtClassName, edtDescription;
    Button btnAdd;

    ClassroomDAO classroomDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        findView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtClassName.getText().toString().trim();
                String description = edtDescription.getText().toString().trim();
                if(name.isEmpty()||description.isEmpty()) {
                    Snackbar.make(btnAdd, "Vui lòng điền đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
                } else {
                    Classroom newClass = new Classroom(name, description);
                    classroomDAO = new ClassroomDAO(AddClassActivity.this);
                    boolean addSuccess = classroomDAO.addNewClass(newClass);
                    if(addSuccess) finish();
                    else Snackbar.make(btnAdd, "Thêm lớp không thành công!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void findView() {
        edtClassName = findViewById(R.id.edt_class_name);
        edtDescription = findViewById(R.id.edt_class_description);
        btnAdd = findViewById(R.id.btn_addclass);
    }
}