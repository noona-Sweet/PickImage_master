package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

public class Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home__page );


        View oa = (View) findViewById(R.id.vi1);
        oa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent or = new Intent(Home_Page.this, Organization_advertisement.class);
            startActivity(or);
        }
    });
    View ob = (View) findViewById(R.id.vi2);
        ob.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent oh = new Intent(Home_Page.this, Health_Volunteering.class);
            startActivity(oh);
        }
    });
    View oc = (View) findViewById(R.id.vi3);
        oc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent occ = new Intent(Home_Page.this,Environmental_Volunteering.class);
            startActivity(occ);
        }
    });
    View od = (View) findViewById(R.id.vi4);
        od.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent odd = new Intent(Home_Page.this,Educational_Volunteering.class);
            startActivity(odd);
        }
    });

    Toolbar toool = (Toolbar) findViewById(R.id.toolbar);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_org, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent(Home_Page.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent(Home_Page.this, Home_Page.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText(Home_Page.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent(Home_Page.this, SampleActivity.class);
            startActivity(edit);
        }
        if (id == R.id.adds) {

            Intent add = new Intent(Home_Page.this, Add_Advertisement.class);
            startActivity(add);
        }
        if (id == R.id.pay) {
            Intent payment = new Intent( Home_Page.this, Payment_Setting.class );
            startActivity( payment );
        }
        if(id == R.id.hs) {
            Intent help = new Intent(Home_Page.this, Help_Support.class);
            startActivity(help);
        }
        if (id == R.id.lo) {
            Intent loog = new Intent(Home_Page.this,Welcome.class);
            startActivity(loog);
        }
        return true;
    }
    }



