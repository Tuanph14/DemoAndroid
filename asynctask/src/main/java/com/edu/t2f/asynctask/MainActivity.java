package com.edu.t2f.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Country> countries;
    Button btnNormal,btnAsynctask;
    MyAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        btnAsynctask = (Button) findViewById(R.id.btnAsynctask);
        btnNormal = (Button) findViewById(R.id.btnNormal);

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Country> countries = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    try {
                        Thread.sleep(1000);
                        countries.add(new Country("Vietnam -" + i, R.drawable.vietnam));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                MyAdapter adapter = new MyAdapter(MainActivity.this,R.layout.customlistview,countries);
                listView.setAdapter(adapter);
            }

        });

        btnAsynctask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
                AsyncTask<String, Country,Void> asyncTask = new AsyncTask<String, Country, Void>() {
                    @Override
                    protected Void doInBackground(String... params) {
                        for (int i = 0; i < params.length; i++) {
                            try {
                                Thread.sleep(1000);

                                Country country = new Country("Vietnam - "+i,R.drawable.vietnam);
                                publishProgress(country);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Country... values) {
                        super.onProgressUpdate(values);
                        countries.add(values[0]);
                        madapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
                    }
                };

//                asyncTask.execute("country1","country2","country3","country4",
//                        "country5","country6","country7","country8","country9");
                String s [] = new String[12];
                asyncTask.execute(s);
            }
        });
    }

    void initData() {
        countries = new ArrayList<>();
        countries.add(new Country("Vietnam", R.drawable.vietnam));
        madapter = new MyAdapter(MainActivity.this,R.layout.customlistview,countries);
        listView.setAdapter(madapter);
    }
}
