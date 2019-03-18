package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;

public class Volunteersl extends AppCompatActivity {



    private Post_Adapter_Volunteer post_adapter;

    private ArrayList<Postitems> postitems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_volunteersl );


        Toolbar toool = (Toolbar) findViewById(R.id.toolbar);


        RecyclerView recycleerView = (RecyclerView) findViewById(R.id.recyclervol);
        postitems = new ArrayList<>();
        for (int i = 0; i<5; i++) {
            postitems.add(new Postitems("! We Need Volunteers  ", ""));
        }

        post_adapter = new Post_Adapter_Volunteer(this, postitems);

        recycleerView.setLayoutManager(new LinearLayoutManager(this));
        recycleerView.setAdapter(post_adapter);

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
            Intent messaage = new Intent( Volunteersl.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent( Volunteersl.this, Voulnteers.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText(Volunteersl.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent(Volunteersl.this, SampleActivity.class);
            startActivity(edit);
        }
        if (id == R.id.changep) {
            Intent change = new Intent( Volunteersl.this, Change_Password.class );
            startActivity( change );
        }
        if(id == R.id.hs) {
            Intent help = new Intent(Volunteersl.this, Help_Support.class);
            startActivity(help);
        }
        if (id == R.id.lo) {
            Intent loog = new Intent(Volunteersl.this,Welcome.class);
            startActivity(loog);
        }
        return true;
    }

}
