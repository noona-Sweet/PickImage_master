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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vansuita.pickimage.sample.R;

public class Forget_Password extends AppCompatActivity {
    private TextInputEditText textInputEditTextEmail;
    private TextInputLayout textInputLayoutEmail;

    private AppCompatButton appCompatButtonConfirm;

    private Input_validation inputValidation;
    private Database_Helper helper;

    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_forget__password );


        Toolbar toool = (Toolbar) findViewById(R.id.toolbar);


        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
            appCompatButtonConfirm = (AppCompatButton) findViewById(R.id.appCompatButtonConfirm);
            nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

            helper = new Database_Helper(this);
            inputValidation = new Input_validation(this);

            setTitle("Recover password");
            appCompatButtonConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    verifyFromSQLite();
                }
            });

        }

        private void verifyFromSQLite(){

            if (textInputEditTextEmail.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
                return;
            }


            if (helper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
                Intent resetIntent = new Intent(this, Change_Password.class);
                resetIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                emptyInputEditText();
                startActivity(resetIntent);
            } else {
                Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            }
        }

        private void emptyInputEditText(){
            textInputEditTextEmail.setText("");
        }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent( Forget_Password.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent( Forget_Password.this, Voulnteers.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText( Forget_Password.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.register) {
            Intent reg = new Intent( Forget_Password.this, MainActivity.class);
            startActivity(reg);
        }
        if (id == R.id.Loginn) {
            Intent change = new Intent( Forget_Password.this, Login.class );
            startActivity( change );
        }
        return true;
    }
    }

