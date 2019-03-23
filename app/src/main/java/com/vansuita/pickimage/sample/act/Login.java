package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import static com.vansuita.pickimage.sample.act.AppSharedPreferences.USER_TYPE;
import static com.vansuita.pickimage.sample.act.AppSharedPreferences.U_ID;

public class Login extends AppCompatActivity {
    Database_Helper helper;
    Button btnlog;
    EditText a,b;
    Spinner logs;
    TextView forget;
    public static final String EXTRA_TEXT = "com.vansuita.pickimage.sample.act.EXTRA_TEXT";

    AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        helper = new Database_Helper( this );
        appSharedPreferences = new AppSharedPreferences(this);
        logs = (Spinner) findViewById( R.id.spinlog );
        btnlog = (Button) findViewById( R.id.logiin );
        forget = (TextView)findViewById( R.id.forgetpass );


        logs.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                btnlog.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (v.getId() == R.id.logiin) {
                            a = (EditText) findViewById( R.id.user_name2 );
                            String str = a.getText().toString();
                            b = (EditText) findViewById( R.id.passo );
                            String pss = b.getText().toString();

                            String password = helper.searchpass( str );
                            if (pss.equals( password ) && (position == 0) && helper.getUserType(str).equals("org")) {

                                appSharedPreferences.writeString(U_ID, str);
                                appSharedPreferences.writeString(USER_TYPE, "org");
                                Intent i = new Intent( Login.this,Home_Page.class );
                                Toast mp = Toast.makeText( Login.this, "Welcome Back " + str, Toast.LENGTH_SHORT );
                                mp.show();
                                i.putExtra(EXTRA_TEXT,"Welcome   " +  str);
                                startActivity( i );
                                finish();

                            }else if (pss.equals( password ) && (position == 1) && helper.getUserType(str).equals("vol")){

                                appSharedPreferences.writeString(U_ID, str);
                                appSharedPreferences.writeString(USER_TYPE, "vol");
                                Intent inte= new Intent( getApplicationContext(), Voulnteers.class );
                                Toast mp = Toast.makeText( Login.this, "Welcome Back " + str, Toast.LENGTH_SHORT );
                                mp.show();
                                inte.putExtra(EXTRA_TEXT,"Welcome   " +  str);
                                startActivity( inte );

                            } else {
                            Toast mp = Toast.makeText( Login.this, " User Name And Passwords Don't Match or user type is wrong", Toast.LENGTH_LONG );
                            mp.show();
                        }
                    }
                } });}
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
        } );

        forget.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent inte= new Intent( Login.this, Forget_Password.class );
                startActivity( inte );

            }
        } );
    }}

