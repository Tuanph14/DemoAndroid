package com.h.tuan.demoandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               doOpenNext();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khoi tao lai Activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
    }

        public void doOpenNext(){
            Intent myintent = new Intent(this,B_activity.class);
            startActivity(myintent);
        }
}
