package databasecountry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.t2f.databasecountry.Country;

/**
 * Created by tuan on 06/02/2016.
 */
public class DatabaseCountry extends SQLiteOpenHelper {

    static final String DB_NAME = "Country.db";
    static final int DB_VERSION = 1;



    public DatabaseCountry(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Country.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Country.DROP_TABLE);
        onCreate(db);
    }

    public Cursor Getdata(String sql){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        return cursor;
    }

    public void QueryData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

}
