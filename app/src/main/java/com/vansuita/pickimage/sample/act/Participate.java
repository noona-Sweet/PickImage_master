package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Participate extends AppCompatActivity {

    ListView partview;
    ArrayList<Contact> clist;
   PartAdapter padapter = null;
    ImageView imagevicon,vieww,icount;
    TextView vname;
    String  uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("BUNYAN");

        Database_Helper phelper = new Database_Helper(this);

        partview = (ListView) findViewById(R.id.plists);
        vname = (TextView) findViewById(R.id.name1);


    }
}
