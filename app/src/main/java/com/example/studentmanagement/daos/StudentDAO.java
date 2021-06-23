package com.example.studentmanagement.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.studentmanagement.DBHelper;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;

public class StudentDAO {
    SQLiteDatabase db;

    public StudentDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // insert
    public boolean addNewStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(Student.COLUMN_NAME, student.getHoTen());
        values.put(Student.COLUMN_ADDRESS, student.getAddress());
        values.put(Student.COLUMN_YEAR_BIRTH, student.getNamSinh());
        values.put(Student.COLUMN_SCHOOL_YEAR, student.getNamhoc());

        return db.insert(Student.TABLE_NAME, null, values) > 0;
    }

    // get all classroom
    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM " + Student.TABLE_NAME;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor!=null && cursor.moveToFirst()) {
                do {
                    Student student = new Student();
                    student.setId(cursor.getInt(cursor.getColumnIndex(Student.COLUMN_ID)));
                    student.setHoTen(cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)));
                    student.setAddress(cursor.getString(cursor.getColumnIndex(Student.COLUMN_ADDRESS)));
                    student.setNamhoc(cursor.getString(cursor.getColumnIndex(Student.COLUMN_SCHOOL_YEAR)));
                    student.setNamSinh(cursor.getInt(cursor.getColumnIndex(Student.COLUMN_YEAR_BIRTH)));
                    studentList.add(student);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return studentList;
    }
}
