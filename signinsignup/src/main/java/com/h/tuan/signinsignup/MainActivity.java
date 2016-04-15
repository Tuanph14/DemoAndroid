package com.h.tuan.signinsignup;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnlogin;
    TextView tvEmail;
    TextView tvUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlogin = (Button) findViewById(R.id.btnLogin);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvEmail.getText().toString().equals("ttt")&&tvUsername.getText().toString().equals("nam"))
                    Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
