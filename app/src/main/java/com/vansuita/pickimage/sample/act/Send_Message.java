package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vansuita.pickimage.sample.R;

public class Send_Message extends AppCompatActivity {

    Toolbar toool;
    EditText etContent, etRecipient;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_send__message );

        toool = (Toolbar) findViewById(R.id.toolbar);
        etContent = (EditText) findViewById(R.id.etContent);
        etRecipient = (EditText)findViewById(R.id.etRecipient);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }


    private void sendMessage() {
        final ProgressDialog dialog = new ProgressDialog(Send_Message.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("youremailaddress", "yourpassword");
                    sender.sendMail("EmailSender App",
                            etContent.getText().toString(),
                            "youremailaddress",
                            etRecipient.getText().toString());
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_org, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent( Send_Message.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent( Send_Message.this, Home_Page.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText( Send_Message.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent( Send_Message.this, SampleActivity.class);
            startActivity(edit);
        }
        if (id == R.id.adds) {

            Intent add = new Intent( Send_Message.this, Add_Advertisement.class );
            startActivity( add );

        }

        if (id == R.id.hs) {
            Intent help = new Intent( Send_Message.this, Help_Support.class);
            startActivity(help);
        }
        if (id == R.id.lo) {
            Intent loog = new Intent(Send_Message.this, Logout.class);
            startActivity(loog);
        }
        return true;
    }
}
