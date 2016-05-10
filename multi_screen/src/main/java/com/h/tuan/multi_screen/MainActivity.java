package com.h.tuan.multi_screen;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvcolor;
    TextView tvandroid;
    TextView stringFormat;
    TextView stringPlural;
    EditText edtcount;
    Button btnsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvcolor = (TextView)findViewById(R.id.tvcolor);
        String str = getString(R.string.color);
        tvcolor.setText(Html.fromHtml(str));

        tvandroid = (TextView)findViewById(R.id.tvandroid);
        String strandroid = getString(R.string.android);
        tvandroid.setText(Html.fromHtml(strandroid));

        stringFormat = (TextView) findViewById(R.id.stringFormat);
        stringPlural = (TextView) findViewById(R.id.stringPlural);
        edtcount = (EditText) findViewById(R.id.edtcount);
        btnsubmit = (Button) findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources resources = getResources();
                int count = Integer.parseInt(edtcount.getText().toString());

                String Format = getString(R.string.res_string_format,"midu",count);
                stringFormat.setText(String.valueOf(Format));

                String Plural = resources.getQuantityString(R.plurals.res_plural_format,count,count);
                stringPlural.setText(String.valueOf(Plural));
            }
        });


    }
}
