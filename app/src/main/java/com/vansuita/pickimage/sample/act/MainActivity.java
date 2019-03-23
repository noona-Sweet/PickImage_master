package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

import static com.vansuita.pickimage.sample.act.AppSharedPreferences.USER_TYPE;
import static com.vansuita.pickimage.sample.act.AppSharedPreferences.U_ID;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.vansuita.pickimage.sample.act.EXTRA_TEXT";


    Database_Helper helper = new Database_Helper(this);

    AppSharedPreferences appSharedPreferences = new AppSharedPreferences(this);

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



                    if (TextUtils.isEmpty(uname) || uemail.isEmpty() || ucall.isEmpty() || (!chk1.isChecked() && !chk2.isChecked()) || !upass.equals(ucpass)) {
                        //pop up message
                        Toast pass = Toast.makeText(MainActivity.this, "One of fields is/are empty or passwords don't match", Toast.LENGTH_LONG);
                        pass.show();
                    } else {
                        //enter the data inside the database.
                        Contact c = new Contact();
                        c.setUname(uname);
                        c.setEmail(uemail);
                        c.setPhone(ucall);
                        c.setPassword(upass);
                        c.setCpass(ucpass);
                        if (chk1.isChecked()) {
                            chk2.setChecked(false);
                            c.setUserType("org");
                        } else if (chk2.isChecked()) {
                            chk1.setChecked(false);
                            c.setUserType("vol");
                        }

                        helper.insertContact( c );

                        Toast mp = Toast.makeText( MainActivity.this, "Welcome  "+uname, Toast.LENGTH_SHORT );
                        mp.show();
                        if (chk1.isChecked()) {
                            chk2.setChecked(false);
                            appSharedPreferences.writeString(U_ID, uname);
                            appSharedPreferences.writeString(USER_TYPE, "org");
                            Intent c1 = new Intent(MainActivity.this, Home_Page.class);
                            c1.putExtra(EXTRA_TEXT,"Welcome   " +  uname);
                            startActivity(c1);
                        } else {
                            Toast.makeText(MainActivity.this, "Please Choose Option", Toast.LENGTH_SHORT).show();

                        }

                        if (chk2.isChecked()) {
                            chk1.setChecked(false);
                            appSharedPreferences.writeString(U_ID, uname);
                            appSharedPreferences.writeString(USER_TYPE, "vol");
                            Intent c2 = new Intent(MainActivity.this, Voulnteers.class);
                            c2.putExtra(EXTRA_TEXT,"Welcome   " +  uname);
                            startActivity(c2);
                        } else {
                            Toast.makeText(MainActivity.this, "Please Choose Option", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }
        });

        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    chk2.setChecked(false);
                }
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    chk1.setChecked(false);
                }
            }
        });

    }

    public void Loginn(View view) {
        Intent ll = new Intent(MainActivity.this,Login.class);
        startActivity(ll);

    }}
