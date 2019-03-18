package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome_back );

        View ob = (View) findViewById(R.id.vi2);
        ob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( Welcome.this,"Please Signup or login to see the Advertise",Toast.LENGTH_SHORT ).show();
            }
        });
        View oc = (View) findViewById(R.id.vi3);
        oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( Welcome.this,"Please Signup or login to see the Advertise",Toast.LENGTH_SHORT ).show();
            }
        });
        View od = (View) findViewById(R.id.vi4);
        od.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( Welcome.this,"Please Signup or login to see the Advertise",Toast.LENGTH_SHORT ).show();
            }
        });

        Toolbar toool = (Toolbar) findViewById(R.id.toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent( Welcome.this, Messages.class);
            startActivity(messaage);
        }
        if (id ==  R.id.homee ) {
            Intent hpp = new Intent( Welcome.this, Welcome.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText( Welcome.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.register) {
            Intent reg = new Intent( Welcome.this, MainActivity.class);
            startActivity(reg);
        }
        if (id == R.id.Loginn) {
            Intent change = new Intent( Welcome.this, Login.class );
            startActivity( change );
        }
        return true;
    }
}
