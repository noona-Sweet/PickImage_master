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

public class Voulnteers extends AppCompatActivity {
    TextView welv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_voulnteers );

        Toolbar toool = (Toolbar) findViewById(R.id.toolbar);

        Intent intent = getIntent();
        welv = (TextView)findViewById(R.id.welcomev);
        String tv12 = intent.getStringExtra(MainActivity. EXTRA_TEXT);
        welv.setText(tv12);

        View ob = (View) findViewById(R.id.vi2);
        ob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oh = new Intent(Voulnteers.this, Health_Volunteering.class);
                startActivity(oh);
            }
        });
        View oc = (View) findViewById(R.id.vi3);
        oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent occ = new Intent(Voulnteers.this,Environmental_Volunteering.class);
                startActivity(occ);
            }
        });
        View od = (View) findViewById(R.id.vi4);
        od.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent odd = new Intent(Voulnteers.this,Educational_Volunteering.class);
                startActivity(odd);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_volunteer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent(Voulnteers.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent(Voulnteers.this, Voulnteers.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText(Voulnteers.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent(Voulnteers.this, SampleActivity.class);
            startActivity(edit);
        }
        if(id == R.id.hs) {
            Intent help = new Intent(Voulnteers.this, Help_Support.class);
            startActivity(help);
        }
        if (id == R.id.lo) {
            Intent loog = new Intent(Voulnteers.this, Welcome.class);
            startActivity(loog);
        }
        return true;
    }
}
