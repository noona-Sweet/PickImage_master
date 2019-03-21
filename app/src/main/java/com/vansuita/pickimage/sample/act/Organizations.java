package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;

public class Organizations extends AppCompatActivity {

    ImageView aaccept, rrecomend, viiew, paart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_organizations );

        Toolbar toool = (Toolbar) findViewById( R.id.toolbar );

        rrecomend = (ImageView) findViewById( R.id.recomend );
        viiew = (ImageView) findViewById( R.id.vieew );
        paart = (ImageView) findViewById( R.id.participate );
    }

    public void Views(View view) {
        Intent rec = new Intent( Organizations.this,Views.class );
        startActivity( rec );
    }


    public void Recomended(View view) {

        Intent rec = new Intent( Organizations.this, Recomended.class );
        startActivity( rec );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_org, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent( Organizations.this, Messages.class );
            startActivity( messaage );
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent( Organizations.this, Home_Page.class );
            startActivity( hpp );
        }
        if (id == R.id.options) {
            Toast.makeText( Organizations.this, "choose Option", Toast.LENGTH_SHORT ).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent( Organizations.this, SampleActivity.class );
            startActivity( edit );
        }
        if (id == R.id.adds) {

            Intent add = new Intent( Organizations.this, Add_Advertisement.class );
            startActivity( add );
        }
        if (id == R.id.pay) {
            Intent payment = new Intent( Organizations.this, Payment_Setting.class );
            startActivity( payment );
        }
        if (id == R.id.changep) {
            Intent change = new Intent( Organizations.this, Change_Password.class );
            startActivity( change );
        }
        if (id == R.id.hs) {
            Intent help = new Intent( Organizations.this, Help_Support.class );
            startActivity( help );
        }
        if (id == R.id.lo) {
            Intent loog = new Intent( Organizations.this, Welcome.class );
            startActivity( loog );
        }
        return true;
    }


}