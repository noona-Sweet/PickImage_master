package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.vansuita.pickimage.sample.R;

public class Views extends AppCompatActivity {

    TextView viiews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_views );

        viiews = (TextView)findViewById(R.id.txtviews);

        Intent intent = getIntent();
        viiews.setText(intent.getStringExtra("viiews"));










    }
}
