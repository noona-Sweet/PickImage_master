package com.vansuita.pickimage.sample.act;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.sample.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import static com.vansuita.pickimage.sample.R.layout.activity_add__advertisement;

public class Add_Advertisement extends AppCompatActivity {

    Spinner spinnerr;
   EditText title, description, phone;
    String titles, desrip,phons;
    Toolbar toool;
    Button subb,locate;
    ImageView images;
    Database_Helper helper = new Database_Helper(this);

    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelper mSqlitehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( activity_add__advertisement );

        toool = (Toolbar) findViewById(R.id.toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle( "New Advertisement" );


        //create database
        mSqlitehelper = new  SQLiteHelper(this,"RECORDDB.sqlite",null,1);

//create table in database
        mSqlitehelper.querydata( "CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR," +
                " description VARCHAR, phone VARCHAR, image BLOB)" );
        title = (EditText) findViewById(R.id.tpt);
        description = (EditText) findViewById(R.id.descc);
        phone = (EditText) findViewById(R.id.phoone);
        images= (ImageView) findViewById(R.id.uploadimg);
        spinnerr = (Spinner) findViewById(R.id.spinner);

        images.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions( Add_Advertisement.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );

            }
        } );
        locate = (Button) findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oo = new Intent( Add_Advertisement.this, Location.class);
                startActivity(oo);
            }
        });



        spinnerr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                subb = (Button) findViewById( R.id.sub );
                subb.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {


                        titles = title.getText().toString().trim();
                        desrip = description.getText().toString().trim();
                        phons = phone.getText().toString().trim();
                        if (position == 0) {

                            Intent oones = new Intent( Add_Advertisement.this, Organization_advertisement.class );
                            mSqlitehelper.insertData( title.getText().toString().trim(), description.getText().toString().trim(), phone.getText().toString().trim(), imageViewToByte( images ) );
                            Toast.makeText( Add_Advertisement.this, "Added Successfully", Toast.LENGTH_SHORT ).show();
                            title.setText( " " );
                            description.setText( " " );
                            phone.setText( " " );
                            images.setImageResource( R.drawable.addphoto );
                            startActivity( oones );
                        } else {
                            Toast.makeText( Add_Advertisement.this, "No Data", Toast.LENGTH_SHORT ).show();

                            if (position == 1) {

                                Intent two = new Intent( Add_Advertisement.this, Health_Volunteering.class );
                                mSqlitehelper.insertData( title.getText().toString().trim(), description.getText().toString().trim(), phone.getText().toString().trim(), imageViewToByte( images ) );
                                Toast.makeText( Add_Advertisement.this, "Added Successfully", Toast.LENGTH_SHORT ).show();
                                title.setText( " " );
                                description.setText( " " );
                                phone.setText( " " );
                                images.setImageResource( R.drawable.addphoto );
                                startActivity( two );

                            } else {
                                Toast.makeText( Add_Advertisement.this, "No Data", Toast.LENGTH_SHORT ).show();

                                if (position == 2) {

                                    Intent two = new Intent( Add_Advertisement.this, Environmental_Volunteering.class );
                                    mSqlitehelper.insertData( title.getText().toString().trim(), description.getText().toString().trim(), phone.getText().toString().trim(), imageViewToByte( images ) );
                                    Toast.makeText( Add_Advertisement.this, "Added Successfully", Toast.LENGTH_SHORT ).show();
                                    title.setText( " " );
                                    description.setText( " " );
                                    phone.setText( " " );
                                    images.setImageResource( R.drawable.addphoto );
                                    startActivity( two );

                                } else {
                                    Toast.makeText( Add_Advertisement.this, "No Data", Toast.LENGTH_SHORT ).show();

                                    if (position == 3) {

                                        Intent it = new Intent( Add_Advertisement.this, Educational_Volunteering.class );

                                        mSqlitehelper.insertData( title.getText().toString().trim(), description.getText().toString().trim(), phone.getText().toString().trim(), imageViewToByte( images ) );
                                        Toast.makeText( Add_Advertisement.this, "Added Successfully", Toast.LENGTH_SHORT ).show();
                                        title.setText( " " );
                                        description.setText( " " );
                                        phone.setText( " " );
                                        images.setImageResource( R.drawable.addphoto );
                                        startActivity( it );

                                    } else {
                                        Toast.makeText( Add_Advertisement.this, "No Data", Toast.LENGTH_SHORT ).show();

                                    }
                                }
                            }
                        }
                    }

                } );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });}
            public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_org, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent( Add_Advertisement.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent( Add_Advertisement.this, Home_Page.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText( Add_Advertisement.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent( Add_Advertisement.this, SampleActivity.class);
            startActivity(edit);
        }
        if (id == R.id.adds) {

            Intent add = new Intent( Add_Advertisement.this, Add_Advertisement.class );
            startActivity( add );

        }

        if (id == R.id.hs) {
            Intent help = new Intent( Add_Advertisement.this, Help_Support.class);
            startActivity(help);
        }
        if (id == R.id.lo) {
            Intent loog = new Intent(Add_Advertisement.this, Welcome.class);
            startActivity(loog);
        }
        return true;
    }
    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream );
        byte[] bytesArray = stream.toByteArray();
        return bytesArray;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent galleryintent = new Intent( Intent.ACTION_GET_CONTENT );
                galleryintent.setType( "image/*" );
                startActivityForResult( galleryintent, REQUEST_CODE_GALLERY );
            } else {
                Toast.makeText( this, "Don't Have Permission to Access File Location", Toast.LENGTH_SHORT ).show();
            }
            return;

        }
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();
            CropImage.activity( imageUri ).setGuidelines
                    ( CropImageView.Guidelines.ON ).setAspectRatio( 1, 1 )
                    .start( this );
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult( data );
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                images.setImageURI( resultUri );
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
        super.onActivityResult( requestCode, resultCode, data );
    }



















}

