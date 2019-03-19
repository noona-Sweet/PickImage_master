package com.vansuita.pickimage.sample.act;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.sample.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

public class Health_Volunteering extends AppCompatActivity {

    private int mcount = 0;
    ListView llistview;
    ArrayList<AdvertisV> lists;
   HealthAdapter adapter = null;
    ImageView imagevicon,vieww,participateh;
    TextView count;
    public static SQLiteHelper mSqlitehelper;
    TextView tv11;


    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        Drawable drawable = image.getDrawable();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytesArray = stream.toByteArray();
        return bytesArray;


    }

    public void showupdatedialog(Activity activity, final int position) {
        final Dialog dialog = new Dialog( activity );
        dialog.setContentView( R.layout.update);
        dialog.setTitle( "Update" );

        imagevicon = dialog.findViewById( R.id.imageup );
        final EditText vtitle = dialog.findViewById( R.id.updatet );
        final EditText vdescrip = dialog.findViewById( R.id.updated );
        final EditText vphone = dialog.findViewById( R.id.updatep );
        final Button updatess = dialog.findViewById( R.id.updates );

        // get data from row on clicked from sql

        Cursor cursor = mSqlitehelper.getData( " SELECT * FROM RECORD WHERE id="+position );
       lists.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt( 0 );
            String ti = cursor.getString( 1 );
            vtitle.setText(ti);
            String de = cursor.getString( 2 );
            vdescrip.setText(de);
            String ph = cursor.getString( 3 );
            vphone.setText(ph);
            byte[] im = cursor.getBlob( 4 );
            imagevicon.setImageBitmap( BitmapFactory.decodeByteArray( im,0,im.length ) );
            //adds to list
            lists.add( new AdvertisV( id, ti, de, ph, im ));
        }

        // set width & height of alertdialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout( width, height );
        dialog.show();

        //to update img view
        imagevicon.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check external storage permissions

                ActivityCompat.requestPermissions(
                        Health_Volunteering.this, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        } );
        updatess.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                   Health_Volunteering.mSqlitehelper.updateData(
                            vtitle.getText().toString().trim(),
                            vdescrip.getText().toString().trim(),
                            vphone.getText().toString().trim(),
                            imageViewToByte( imagevicon ),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText( Health_Volunteering.this,"Update Successfull",Toast.LENGTH_SHORT ).show();
                }
                catch (Exception error){

                    Log.e( "Error Update",error.getMessage());
                }
                updateRecordList();
            }
        } );


    }

    private void showdeletdialog(final int idrecord) {

        AlertDialog.Builder deletedialog = new AlertDialog.Builder( Health_Volunteering.this );
        deletedialog.setTitle( " Warning!!" );
        deletedialog.setMessage( " Are you sure you want Delete?" );
        deletedialog.setPositiveButton( " OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Add_Advertisement.mSqlitehelper.deleteData(idrecord);
                    Toast.makeText(Health_Volunteering.this,"Deleted successfully",Toast.LENGTH_SHORT ).show();
                }catch (Exception e){
                    Log.e( "error", e.getMessage());
                }
                updateRecordList();
            }
        });
        deletedialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        } );
        deletedialog.show();


    }

    private void updateRecordList() {

        //call all data from sql

        Cursor cursor = Health_Volunteering.mSqlitehelper.getData(  "SELECT * FROM RECORD");
      lists.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt( 0 );
            String titles = cursor.getString( 1 );
            String describe = cursor.getString( 2 );
            String phones = cursor.getString( 3 );
            byte[] imgs = cursor.getBlob( 4 );
            //adds to list
           lists.add( new AdvertisV( id, titles, describe, phones, imgs ) );
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__volunteering);

        Toolbar toool = findViewById(R.id.toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Advertisement List");

        llistview = findViewById(R.id.listviewv);
        vieww = findViewById(R.id.imgiicon);
        count = findViewById(R.id.counts);
        lists = new ArrayList<AdvertisV>();
        adapter = new HealthAdapter(this, R.layout.row_vol, lists);
        llistview.setAdapter(adapter);

        // get all data from sql

        mSqlitehelper = new SQLiteHelper(this, "RECORDDB.sqlite", null, 1);
        mSqlitehelper.querydata("CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR," +
                " description VARCHAR, phone VARCHAR, image BLOB, category INTEGER)");

        Cursor cursor = Health_Volunteering.mSqlitehelper.getData(" SELECT * FROM RECORD WHERE category = 1");
        lists.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ti = cursor.getString(1);
            String de = cursor.getString(2);
            String ph = cursor.getString(3);
            byte[] im = cursor.getBlob(4);
            //adds to list
            lists.add(new AdvertisV(id, ti, de, ph, im));
        }
        adapter.notifyDataSetChanged();
        if (lists.size() == 0) {
            // if the list is empty and no records found in the table
            Toast.makeText(this, "No Record Found...", Toast.LENGTH_SHORT).show();
        }


        llistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(Health_Volunteering.this);

                dialog.setTitle(" Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //update
                            Cursor c = Add_Advertisement.mSqlitehelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> attID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                attID.add(c.getInt(0));
                            }

                            //show dialog update
                            showupdatedialog(Health_Volunteering.this, attID.get(position));
                        }
                        if (i == 1) {
                            //delete

                            Cursor c = Health_Volunteering.mSqlitehelper.getData(" SELECT id FROM RECORD");
                            ArrayList<Integer> attID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                attID.add(c.getInt(0));
                            }
                            showdeletdialog(attID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 888) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent galleryintent = new Intent( Intent.ACTION_GET_CONTENT );
                galleryintent.setType( "image/*" );
                startActivityForResult( galleryintent, 888 );
            } else {
                Toast.makeText( this, "Don't Have Permission to Access File Location", Toast.LENGTH_SHORT ).show();
            }
            return;

        }
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();
            CropImage.activity( imageUri ).setGuidelines( CropImageView.Guidelines.ON ).setAspectRatio( 1, 1 ).start( this );
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult( data );
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();
                imagevicon.setImageURI( resultUri );
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }

        public void Viewsh(View view) {
            Intent rec = new Intent( Health_Volunteering.this,Views.class );
            startActivity( rec );
        }
        public void Acceptableh(View view) {

            Intent acc = new Intent( Health_Volunteering.this, Acceptable.class );
            startActivity( acc );

        }
        public void participateh(View view) {

            Intent parts = new Intent( Health_Volunteering.this, Participate.class );
            startActivity( parts );
        }

        public void Recomendedh(View view) {

            Intent rec = new Intent( Health_Volunteering.this, Recomended.class );
            startActivity( rec );
        }

        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_volunteer, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.msg) {
                Intent messaage = new Intent(Health_Volunteering.this, Messages.class);
                startActivity(messaage);
            }
            if (id == R.id.homee) {
                Intent hpp = new Intent(Health_Volunteering.this, Voulnteers.class);
                startActivity(hpp);
            }
            if (id == R.id.options) {
                Toast.makeText(Health_Volunteering.this, "choose Option", Toast.LENGTH_SHORT).show();
            }
            if (id == R.id.edits) {
                Intent edit = new Intent(Health_Volunteering.this, SampleActivity.class);
                startActivity(edit);
            }
            if(id == R.id.hs) {
                Intent help = new Intent(Health_Volunteering.this, Help_Support.class);
                startActivity(help);
            }
            if (id == R.id.lo) {
                Intent loog = new Intent(Health_Volunteering.this, Welcome.class);
                startActivity(loog);
            }
            return true;
        }

}
