package com.edu.t2f.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AutoCompleteTextView autoCompleteTextView;
    Myadapter myadapter, adapter ;
    List<String> nameEnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.atcomplete);

        initData();
        myadapter = new Myadapter(this,android.R.layout.simple_list_item_1,nameEnList);
        listView.setAdapter(myadapter);
        adapter = new Myadapter(this, android.R.layout.simple_list_item_1,nameEnList);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        //Mục tiêu: tìm SearchView để bắt sự kiện khi search
        //==> SearchView nằm trong menuItem
        //==> findItem --> getActionView ==> SearchView

        MenuItem mnSearch = menu.findItem(R.id.mnSearch);
        SearchView searchView = (SearchView) mnSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //dc goi khi bam button submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //dc goi khi go phim
            @Override
            public boolean onQueryTextChange(String newText) {
                myadapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }


    void initData() {
        nameEnList = new ArrayList<>();
        nameEnList.add("Vietnam");
        nameEnList.add("China");
        nameEnList.add("Japan");
        nameEnList.add("Korea");
        nameEnList.add("Thailand");
        nameEnList.add("Laos");
        nameEnList.add("Cambodia");
        nameEnList.add("Canada");
        nameEnList.add("Spain");
        nameEnList.add("England");
        nameEnList.add("Italy");
    }
}
