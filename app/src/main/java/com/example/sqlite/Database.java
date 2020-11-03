package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private  final  static  String DATABASE_NAME = "database.db";
    private final static  String TABLE_NAME = "NhanVien";
    private final static String col_1 = "id";
    private final static String col_2 = "name";
    private final static String col_3 = "address";
    private final static String col_4 = "phone";
    private final static String col_5 = "job";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, ADDRESS TEXT, PHONE INTEGER, JOB TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
}

    public boolean insertDatabase(String id,String name, String address, String phone, String job) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_1,id);
        values.put(col_2, name);
        values.put(col_3, address);
        values.put(col_4,phone);
        values.put(col_5, job);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else return true;
    }
    public boolean updateDatabase(String id ,String name, String address, String phone, String job ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_1, id);
        values.put(col_2, name);
        values.put(col_3, address);
        values.put(col_4,phone);
        values.put(col_5, job);
        long result = db.update(TABLE_NAME, values, col_1 + "=?",new String[]{id});
        if (result == -1)
            return false;
        else return true;
    }
    public boolean deleteDatabase(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, col_1 + "=?" , new String[]{id});
        if (result == -1)
            return false;
        else return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
}
