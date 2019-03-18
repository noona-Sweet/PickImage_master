package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

public class Help_Support extends AppCompatActivity {

    TextView help;
    EditText emailh,problemh;
    Button Sendd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_help__support );

        help = (TextView) findViewById( R.id.helptxt );
        emailh = (EditText) findViewById( R.id.emailhelp );
        problemh = (EditText) findViewById( R.id.helpproblem );
        Sendd = (Button) findViewById( R.id.helpbtn );

        Sendd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent( Help_Support.this, Welcome.class );
                startActivity( help );

                Toast.makeText( Help_Support.this, " Your information Send , Thank u see you soon", Toast.LENGTH_SHORT ).show();
            }
        } );

    }}
