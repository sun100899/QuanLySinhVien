package com.example.studentmanagement.model;

public class Student {
    public static final String TABLE_NAME = "SinhVien";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "HoTen";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_SCHOOL_YEAR = "SchoolYear";
    public static final String COLUMN_YEAR_BIRTH = "YearOfBirth";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "( "
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_ADDRESS + " TEXT, "
                    + COLUMN_YEAR_BIRTH + " INTEGER, "
                    + COLUMN_SCHOOL_YEAR + " TEXT"
                    + ")";

    private int id;
    private String hoTen, address, namhoc;
    private int namSinh;

    public Student() {
    }

    public Student(String hoTen, String address, String namhoc, int namSinh) {
        this.hoTen = hoTen;
        this.address = address;
        this.namhoc = namhoc;
        this.namSinh = namSinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
