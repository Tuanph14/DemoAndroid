package com.edu.t2f.database_sample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tuan on 06/03/2016.
 */
public class Database extends SQLiteOpenHelper {

    public final static String TABLE_NAME = "demo.sqlite";

    public Database(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        QueryData("CREATE TABLE Country (ID INTEGER PRIMARY KEY, " +
                "HOTEN VARCHAR(100) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }

    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
}
