package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.vansuita.pickimage.sample.R;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME = 5000; //This is 5 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );


            //Code to start timer and take action after the timer ends
            new Handler().postDelayed( new Runnable() {
                @Override
                public void run() {
                    //Do any action here. Now we are moving to next page
                    Intent mySuperIntent = new Intent(Splash.this, Welcome.class);
                    startActivity(mySuperIntent);
                    /* This 'finish()' is for exiting the app when back button pressed
                     *  from Home page which is ActivityHome
                     */
                    finish();
                }
            }, SPLASH_TIME);
        }
    }