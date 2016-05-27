package com.example.intent_demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tuan on 05/15/2016.
 */
public class MyClickHandler implements View.OnClickListener {
    EditText txtUriString;
    Context context;

    @Override
    public void onClick(View v) {
        try {

            String myData = txtUriString.getText().toString();

            Intent myActivity2 = new Intent(Intent.ACTION_DIAL,
                    Uri.parse(myData));



        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), 	Toast.LENGTH_LONG).show();
        }
    }
}
