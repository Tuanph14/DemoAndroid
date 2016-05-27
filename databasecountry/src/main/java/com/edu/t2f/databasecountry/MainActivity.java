package com.edu.t2f.databasecountry;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //bước 1:copy file db vao bo nho trong
                //kiem tra file db da ton tai hay chu
                // ton tai --> bo qu
                //chua --> copy

                String path = getFilesDir().getAbsolutePath() + "/DBCountry";
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

                //buoc 2: mo file db
                SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(path,
                        null, SQLiteDatabase.OPEN_READONLY);

                //buoc 3: truy van du lieu tu table country
                Cursor cursor = sqLiteDatabase.query(Country.TABLE_NAME, null, null, null, null, null,
                        Country.COL_NAME_EN + " ASC");
                //trong qua trinh truy van co kha nag bi loi hoac trong truy van dc --> null
                List<Country> countries = null;
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        //chac chan la co du lieu --> lay du lieu
                        countries = new ArrayList<>();
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
                    sqLiteDatabase.close();
                }


                //buoc 4: load ds countries len listview
                AdapterCountry countryAdapter = new AdapterCountry(MainActivity.this,
                        R.layout.customelist, countries);
                listView.setAdapter(countryAdapter);
            }
        });
    }


}
