package com.edu.t2f.demoprovider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.t2f.demoprovider.model.Country;

/**
 * Created by tuan on 06/06/2016.
 */
public class DBContext extends SQLiteOpenHelper {

    public final static String DB_NAME = "Country.db";
    public final static int DB_VERSION = 1;


    public DBContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Country.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
