package com.example.custom_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Country> countries;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        initData();

        AdapterCountry adapterCountry = new
                AdapterCountry(this,R.layout.customlistview,countries);

        listView.setAdapter(adapterCountry);

    }

    void initData(){
        countries = new ArrayList<>();
        countries.add(new Country("Vietnam","Việt Nam",R.drawable.vietnam));
        countries.add(new Country("Laos","Lào",R.drawable.lao));
        countries.add(new Country("Japan","Nhật Bản",R.drawable.japan));
        countries.add(new Country("USA","Mỹ",R.drawable.usa));
        countries.add(new Country("Korea","Hàn Quốc",R.drawable.korea));
        countries.add(new Country("China","Trung Quốc",R.drawable.china));
        countries.add(new Country("Mexico","Mê hi cô",R.drawable.mehico));
        countries.add(new Country("India","Ấn Độ",R.drawable.india));
        countries.add(new Country("Italia","Ý",R.drawable.italy));
        countries.add(new Country("French","Pháp",R.drawable.france));
        countries.add(new Country("Taiwan","Đài Loan",R.drawable.taiwan));
        countries.add(new Country("Thailand","Thái Lan",R.drawable.thai));
    }
}
