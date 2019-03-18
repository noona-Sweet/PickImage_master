package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import java.util.ArrayList;
import java.util.List;

public class Environmental_Volunteering extends AppCompatActivity {

    ImageView viiew;

    String Titlee, Descrip, Phonenumber;
    TextView tv11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_health__volunteering );

        Toolbar toool = (Toolbar) findViewById( R.id.toolbar );
    }

}