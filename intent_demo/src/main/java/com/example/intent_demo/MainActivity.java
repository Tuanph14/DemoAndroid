package com.example.intent_demo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUriString;
    Button btnCallActivity2;
    Context context = getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            txtUriString = (EditText) findViewById(R.id.txtUriString);

            btnCallActivity2 = (Button) findViewById(R.id.btnCallActivity2);

            btnCallActivity2.setOnClickListener(new MyClickHandler());

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }
}
