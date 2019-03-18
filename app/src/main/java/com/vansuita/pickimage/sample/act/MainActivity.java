package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

public class MainActivity extends AppCompatActivity {


    Database_Helper helper = new Database_Helper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final CheckBox chk1 = (CheckBox) findViewById(R.id.org);
        final CheckBox chk2 = (CheckBox) findViewById(R.id.vol);
       Button ss = (Button) findViewById(R.id.signupbtn);

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.signupbtn) {
                    EditText etuser = (EditText) findViewById(R.id.usernamee);
                    EditText etmail = (EditText) findViewById(R.id.email);
                    EditText etphone = (EditText) findViewById(R.id.phonee);
                    EditText etpass = (EditText) findViewById(R.id.pass);
                    EditText etcpass = (EditText) findViewById(R.id.cpass);

                    String uname = etuser.getText().toString().trim();
                    String uemail = etmail.getText().toString().trim();
                    String ucall = etphone.getText().toString().trim();
                    String upass = etpass.getText().toString().trim();
                    String ucpass = etcpass.getText().toString().trim();



                    if (!upass.equals(ucpass)) {
                        //pop up message
                        Toast pass = Toast.makeText(MainActivity.this, "Passwords don't match", Toast.LENGTH_SHORT);
                        pass.show();
                    } else {
                        //enter the data inside the database.
                        Contact c = new Contact();
                        c.setUname(uname);
                        c.setEmail(uemail);
                        c.setPhone(ucall);
                        c.setPassword(upass);
                        c.setCpass(ucpass);

                        helper.insertContact( c );

                        Toast mp = Toast.makeText( MainActivity.this, "Welcome  "+uname, Toast.LENGTH_SHORT );
                        mp.show();
                        if (chk1.isChecked()) {
                            Intent c1 = new Intent(MainActivity.this, Home_Page.class);
                            startActivity(c1);
                        } else {
                            Toast.makeText(MainActivity.this, "Please Choose Option", Toast.LENGTH_SHORT).show();
                            if (chk2.isChecked()) {
                                Intent c2 = new Intent(MainActivity.this, Voulnteers.class);
                                startActivity(c2);
                            } else {
                                Toast.makeText(MainActivity.this, "Please Choose Option", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }


            }
        });
    }

    public void Loginn(View view) {
        Intent ll = new Intent(MainActivity.this,Login.class);
        startActivity(ll);

    }}
