package com.edu.t2f.demo_menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.t2f.demo_menu.model.CountryManager;

import java.util.ArrayList;
import java.util.List;

public class OptionActivity extends AppCompatActivity {
    Button btnNext;
    List<String> countrise;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        btnNext = (Button) findViewById(R.id.btnNext);
        listView = (ListView) findViewById(R.id.listview);

        CountryManager countryManager = CountryManager.get();
        countryManager.InitData();
        ArrayList country = countryManager.getCountry();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,countrise);
        listView.setAdapter(adapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OptionActivity.this, ContextMenuActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.mnInsert){
//            Toast.makeText(OptionActivity.this, "Insert", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,CountryActivity.class);
            intent.putExtra(CountryActivity.EXTRA_POSITION,-1);
            startActivityForResult(intent,0);
            return true;
        }else if(id == R.id.mnSearch){
            Toast.makeText(OptionActivity.this, "Search", Toast.LENGTH_SHORT).show();
            return true;

        }else if(id == R.id.mnRefresh){
            Toast.makeText(OptionActivity.this, "Refresh", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

}
