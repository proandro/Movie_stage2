package com.example.lucky.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "favourite.db";
    public static final String TABLE_NAME = "favourite_movie";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "RATING";
    public static final String COL_4 = "DATE";
    public static final String COL_5 = "DESCRIPTION";
    public static final String COL_6 = "IMAGE_URL";
    public static final String COL_7 = "BACK_IMAGE_URL";


    public database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID TEXT PRIMARY KEY ,TITLE TEXT,RATING TEXT,DATE TEXT,DESCRIPTION TEXT,IMAGE_URL TEXT,BACK_IMAGE_URL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String title, String rating, String date, String description, String img_url, String back_img_url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, rating);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, description);
        contentValues.put(COL_6, img_url);
        contentValues.put(COL_7, back_img_url);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}


