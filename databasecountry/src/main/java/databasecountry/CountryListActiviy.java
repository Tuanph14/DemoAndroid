package databasecountry;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.t2f.databasecountry.AdapterCountry;
import com.edu.t2f.databasecountry.Country;
import com.edu.t2f.databasecountry.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryListActiviy extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    Button btnLoad;
    Button btnInsert;
    Button btnDelete;
    Button btnUpdate;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample1);

        listView = (ListView) findViewById(R.id.listview);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDB();
                copyDB();
                setAdapter(getData(newDatabase));

                }
        });

        btnUpdate.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnDelete) {
//            Toast.makeText(CountryListActiviy.this,"Detele",Toast.LENGTH_LONG).show();
            //delete(newDatabase);
            delALl();
        } else if (id == R.id.btnUpdate) {
//            Toast.makeText(CountryListActiviy.this,"Update",Toast.LENGTH_LONG).show();
            update(newDatabase);

        } else if (id == R.id.btnInsert) {
//            Toast.makeText(CountryListActiviy.this,"Insert",Toast.LENGTH_LONG).show();
            Insert(newDatabase);
        }
    }

    void initDB() {

        //copy file database vao trong bo nho trong
        path = getFilesDir().getAbsolutePath() + "/DBCountry";
        File file = new File(path);
        if (!file.exists()) {
            AssetManager assetManager = getAssets();
            try {
                BufferedInputStream bis = new BufferedInputStream(assetManager.open("DBCountry"));
                FileOutputStream fos = openFileOutput("DBCountry", Context.MODE_PRIVATE);

                byte[] buffer = new byte[512];
                int i = 0;
                while ((i = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                }

                bis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        SQLiteDatabase newDatabase;

    void copyDB() {

        //doc file db da co
        List<Country> countries = getData(openDB());

        //copy conten sang file db moi

        DatabaseCountry databaseCountry = new DatabaseCountry(this);
        newDatabase = databaseCountry.getWritableDatabase();

        //check new database da co du lieu --> khong co thi copy
        Cursor cursor = newDatabase.query(Country.TABLE_NAME,null,null,null,null,null,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                return;
            }
            cursor.close();
        }
        newDatabase.beginTransaction();
        for (Country country : countries){
            newDatabase.insert(Country.TABLE_NAME,null,getContenValues(country));
        }
        newDatabase.setTransactionSuccessful();
        newDatabase.endTransaction();
    }

    List<Country> getData(SQLiteDatabase sqLiteDatabase){
        //buoc 2: mo file db
//        SQLiteDatabase sqLiteDatabase = openDB();

        //buoc 3: truy van du lieu tu table country
        Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null, null, null, null, null,
                Country.COL_NAME_EN + " ASC");
        //trong qua trinh truy van co kha nag bi loi hoac trong truy van dc --> null
//        List<Country> countries = null;
        List<Country> countries =  new ArrayList<>();;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                //chac chan la co du lieu --> lay du lieu
//                countries = new ArrayList<>();
                do {
                    Country country = new Country();
                    country.set_id(cursor.getInt(cursor.getColumnIndex(Country.COL_ID)));
                    country.setNameEn(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_EN)));
                    country.setNameVi(cursor.getString(cursor.getColumnIndex(Country.COL_NAME_VI)));
                    country.setFlag(cursor.getString(cursor.getColumnIndex(Country.COL_FLAG)));

                    countries.add(country);
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }
        return countries;
    }

    void setAdapter(List<Country> countries){
        //load ds countries len listview

        AdapterCountry adapterCountry =  new AdapterCountry(CountryListActiviy.this,
                R.layout.activity_sample1,countries);
        listView.setAdapter(adapterCountry);
    }

    //open db
    SQLiteDatabase openDB(){
        return  SQLiteDatabase.openDatabase(path,
                null,SQLiteDatabase.OPEN_READWRITE);
    }

    ContentValues getContenValues(Country country){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Country.COL_NAME_EN,country.getNameEn());
        contentValues.put(Country.COL_NAME_VI,country.getNameVi());
        contentValues.put(Country.COL_FLAG,country.getFlag());

        return  contentValues;
    }


    void Insert (SQLiteDatabase database){

        Country country = new Country();
        long temp = System.currentTimeMillis();
        country.setNameEn("NameEN - " + temp);
        country.setNameVi("NameVI - " + temp);

        //insert tra ve gia tri ID
        long id = database.insert(Country.TABLE_NAME, null,getContenValues(country));
        Log.d("sqlite" ,id +"");
        nameEnlastest = country.getNameEn();
    }
    //lastest id
    String nameEnlastest;

    void update(SQLiteDatabase database){
        Country country = new Country();
        long temp = System.currentTimeMillis();
        country.setNameEn("nameEN - " + temp);
        country.setNameVi("nameVI - " + temp);
        country.setFlag("flag" + temp + ".png");

        int count = database.update(Country.TABLE_NAME, getContenValues(country),
                Country.COL_NAME_EN + " = '?'", new String[]{nameEnlastest} );
        if(count> 0)
            Toast.makeText(CountryListActiviy.this, "update success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(CountryListActiviy.this, "update fail", Toast.LENGTH_SHORT).show();

    }

    void delete (SQLiteDatabase database){
        int count = database.delete(Country.TABLE_NAME,
                Country.COL_NAME_EN + " = " + nameEnlastest,null);

        if(count > 0){
            Toast.makeText(CountryListActiviy.this,"detele success",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(CountryListActiviy.this,"delete fail",Toast.LENGTH_SHORT).show();
        }
    }

    void delALl() {
        SQLiteDatabase database = openDB();
        int count = database.delete(Country.TABLE_NAME, "1 = 1", null);
        if (count > 0)
            Toast.makeText(CountryListActiviy.this, "del success", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(CountryListActiviy.this, "del fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(newDatabase != null){
            newDatabase.close();
        }
    }
}



