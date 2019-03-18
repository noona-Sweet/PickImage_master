package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vansuita.pickimage.sample.R;

public class Change_Password extends AppCompatActivity {

    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private Input_validation inputValidation;
    private Database_Helper helper;
    private NestedScrollView nestedScrollView;
    private AppCompatButton appCompatButtonReset;

    String email;
    Spinner spinnerrs;
    EditText userd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_change__password );


        Toolbar toool = (Toolbar) findViewById( R.id.toolbar );


        inputValidation = new Input_validation( this );
        helper = new Database_Helper( this );

        textInputEditTextPassword = (TextInputEditText) findViewById( R.id.textInputpass );
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById( R.id.textInputConfirmPassword );

        textInputLayoutPassword = (TextInputLayout) findViewById( R.id.textInputLayoutPassword );
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById( R.id.textInputConfirmPass );
        nestedScrollView = (NestedScrollView) findViewById( R.id.nestedScrollView );
        appCompatButtonReset = (AppCompatButton) findViewById( R.id.appCompatButtonReset );
        userd= (EditText)findViewById( R.id.usern );
        Intent intent = getIntent();
        email = intent.getStringExtra( "EMAIL" );


        setTitle( "Reset password" );

        spinnerrs = (Spinner) findViewById(R.id.spinner2);
        spinnerrs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
        appCompatButtonReset.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value1 = textInputEditTextPassword.getText().toString().trim();
                String value2 = textInputEditTextConfirmPassword.getText().toString().trim();
                String usee = userd.getText().toString().trim();

                if (value1.isEmpty() && value2.isEmpty()) {
                    Toast.makeText( Change_Password.this, "fill all fields ", Toast.LENGTH_LONG ).show();

                }else if (!value1.contentEquals( value2 )) {
                    Toast.makeText( Change_Password.this, "password doesn't match", Toast.LENGTH_LONG ).show();

                } else if (value1.equals( value2 ) && (position == 0)) {
                    helper.updatePassword( email, value1 );
                    helper.searchpass( usee );
                    Intent value0 = new Intent( Change_Password.this,Home_Page.class );
                    value0.putExtra("username", usee);
                    Toast.makeText( Change_Password.this, "password reset successfully", Toast.LENGTH_SHORT ).show();
                    emptyInputEditText();
                    startActivity( value0 );

                }else if (value1.equals( value2 ) && (position == 1)) {

                    helper.updatePassword( email, value1 );
                    Intent values = new Intent( Change_Password.this, Voulnteers.class );
                    values.putExtra("username",usee);
                    Toast.makeText( Change_Password.this, "password reset successfully", Toast.LENGTH_SHORT ).show();
                    emptyInputEditText();
                    startActivity( values );
            }
        }});}
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

    }
            private void emptyInputEditText() {
                textInputEditTextPassword.setText( "" );
                textInputEditTextConfirmPassword.setText( "" );
            }

            public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate( R.menu.menu_home, menu );
                return true;
            }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent( Change_Password.this, Messages.class);
            startActivity(messaage);
        }
        if (id ==  R.id.homee ) {
            Intent hpp = new Intent( Change_Password.this, Voulnteers.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText( Change_Password.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.register) {
            Intent reg = new Intent( Change_Password.this, MainActivity.class);
            startActivity(reg);
        }
        if (id == R.id.Loginn) {
            Intent change = new Intent( Change_Password.this, Login.class );
            startActivity( change );
        }
        return true;
    }


}
