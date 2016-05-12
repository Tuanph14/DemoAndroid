package com.example.simple_exlorer;

import android.os.Environment;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    TextView tvpath;
    String mPath;

    ArrayList<String> mFileName;
    ArrayList<String> mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listvietw);
       // listView.setOnClickListener(this);

        mPath = Environment.getExternalStorageDirectory().getPath();

        tvpath = (TextView) findViewById(R.id.textpath);



    }

    public  void getDirectory(String pathDir){

        tvpath.setText(pathDir);

        mFileName = new ArrayList<>();
        mFilePath = new ArrayList<>();

        File dir = new File(pathDir);

        mFileName.add("../");
        mFilePath.add(dir.getParent());

        if(dir.isDirectory()){
            File[] files  = dir.listFiles();
            for (int i=0; i < files.length; i++){
                mFileName.add(files[i].getName());
                mFilePath.add(files[i].getAbsolutePath());

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.activity_main,mFileName);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        File file = new File(mFilePath.get(position));
        if(file.isDirectory()&& !file.isHidden()&&file.canRead()){
            getDirectory(file.getAbsolutePath());
        }else
            Toast.makeText(this, file.getName(),Toast.LENGTH_LONG).show();
    }
}
