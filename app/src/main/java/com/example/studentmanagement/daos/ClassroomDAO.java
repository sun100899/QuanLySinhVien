package com.example.studentmanagement.daos;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.studentmanagement.DBHelper;
import com.example.studentmanagement.model.Classroom;

import java.util.ArrayList;

public class ClassroomDAO {
    SQLiteDatabase db;

    public ClassroomDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // insert
    public boolean addNewClass(Classroom classroom) {
        ContentValues values = new ContentValues();
        values.put(Classroom.COLUMN_NAME, classroom.getName());
        values.put(Classroom.COLUMN_DESCRIPTION, classroom.getDescription());

        return db.insert(Classroom.TABLE_NAME, null, values) > 0;
    }

    // get all classroom
    public ArrayList<Classroom> getAllClassroom() {
        ArrayList<Classroom> classroomList = new ArrayList<>();
        try {
            String selectQuery = "SELECT * FROM " + Classroom.TABLE_NAME;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor!=null && cursor.moveToFirst()) {
                do {
                    Classroom classroom = new Classroom();
                    classroom.setId(cursor.getInt(cursor.getColumnIndex(Classroom.COLUMN_ID)));
                    classroom.setName(cursor.getString(cursor.getColumnIndex(Classroom.COLUMN_NAME)));
                    classroom.setDescription(cursor.getString(cursor.getColumnIndex(Classroom.COLUMN_DESCRIPTION)));

                    classroomList.add(classroom);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
        return classroomList;
    }

    // update
    public boolean updateClassroom(Classroom classroom) {
        ContentValues values = new ContentValues();
        values.put(Classroom.COLUMN_ID, classroom.getId());
        values.put(Classroom.COLUMN_NAME, classroom.getName());
        values.put(Classroom.COLUMN_DESCRIPTION, classroom.getDescription());

        return db.update(Classroom.TABLE_NAME, values, Classroom.COLUMN_ID + " = ?",
                new String[]{String.valueOf(classroom.getId())}) > 0;
    }

    // delete
    public boolean deleteClassById(int id) {
        return db.delete(Classroom.TABLE_NAME, Classroom.COLUMN_ID + " ?", new String[]{String.valueOf(id)}) > 0;
    }
}
