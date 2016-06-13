package com.edu.t2f.demo_menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ContextMenuActivity extends AppCompatActivity {

    ListView listView;
    List<String> countrise;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        listView = (ListView) findViewById(R.id.listview);
        InitData();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countrise);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo contextMenuInfo = 
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = contextMenuInfo.position;
        if(id == R.id.mnUpdat){
            Toast.makeText(ContextMenuActivity.this, "Update", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.mnDel){
            countrise.remove(pos);
            adapter.notifyDataSetInvalidated();
            return true;
        }
        
        return super.onContextItemSelected(item);
    }

    void InitData(){
        countrise = new ArrayList<>();
        countrise.add("Vietnam");
        countrise.add("China");
        countrise.add("Laos");
        countrise.add("Korea");
        countrise.add("USA");
        countrise.add("Canada");
        countrise.add("Italy");
        countrise.add("France");
        countrise.add("England");
        countrise.add("Thailand");
    }

}
