package com.edu.t2f.demoprovider.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.edu.t2f.demoprovider.R;
import com.edu.t2f.demoprovider.database.provider.DataProvider1;
import com.edu.t2f.demoprovider.database.provider.DataProvider2;
import com.edu.t2f.demoprovider.database.provider.DataProvider3;
import com.edu.t2f.demoprovider.database.provider.IDataProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLoadDB, btnLoadDump, btnLoadExistingDB, btnInsertDB;
    ListView listView;
    IDataProvider iDataProvider;
    CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadDB = (Button) findViewById(R.id.btnLoadDB);
        btnInsertDB = (Button) findViewById(R.id.btnInserDB);
        btnLoadDump = (Button) findViewById(R.id.btnLoadDump);
        btnLoadExistingDB = (Button) findViewById(R.id.btnLoadExistingDB);
        listView = (ListView) findViewById(R.id.listView);

        btnLoadDump.setOnClickListener(this);
        btnLoadDB.setOnClickListener(this);
        btnInsertDB.setOnClickListener(this);
        btnLoadExistingDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btnLoadDB) {
            iDataProvider = new DataProvider1(this);

        } else if (id == R.id.btnLoadDump) {
            iDataProvider = new DataProvider2(this);
        } else if (id == R.id.btnLoadExistingDB) {
            iDataProvider = new DataProvider3();
        } else if (id == R.id.btnInserDB) {
            // TODO: Tạo 1 activity insert db vào db trong provider 1

        }
        countryAdapter = new CountryAdapter(this, R.layout.item_list_view_countries,
                iDataProvider.getCountries());
//        countryAdapter.notifyDataSetChanged();
        listView.setAdapter(countryAdapter);

    }
}
