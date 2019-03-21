package com.vansuita.pickimage.sample.act;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.util.SparseBooleanArray;
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

public class Organization_advertisement extends AppCompatActivity  {
    ListView llistview;
    ArrayList<Advertis> mlist;
    OrganizationAdapter madapter = null;
    ImageView imagevicon,d1,vieww,icount;
    TextView  count;
    Database_Helper db;

    public static SQLiteHelper mSqlitehelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_organization_advertisement );
        db = new Database_Helper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("BUNYAN");

        llistview = (ListView) findViewById( R.id.listviewo);
        count = (TextView)findViewById( R.id.counts );
        icount = (ImageView)findViewById( R.id.iconview );
        d1 = (ImageView)findViewById(R.id.dells);
        mlist = new ArrayList<>();
        madapter = new OrganizationAdapter( this, R.layout.row, mlist );
        llistview.setAdapter( madapter );
        imagevicon = (ImageView)findViewById(R.id.imgiicon);

        viewData();

        // get all data from sql
        mSqlitehelper = new  SQLiteHelper(this,"RECORDDB.sqlite",null,1);
        mSqlitehelper.querydata( "CREATE TABLE IF NOT EXISTS RECORD(id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR," +
                " description VARCHAR, phone VARCHAR, image BLOB)" );

        Cursor cursor = Add_Advertisement. mSqlitehelper.getData( " SELECT * FROM RECORD"  );
        mlist.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt( 0 );
            String ti = cursor.getString( 1 );
            String de = cursor.getString( 2 );
            String ph = cursor.getString( 3 );
            byte[] im = cursor.getBlob( 4 );
            //adds to list
            mlist.add( new Advertis( id, ti, de, ph, im ) );
        }
        madapter.notifyDataSetChanged();
        if (mlist.size() == 0) {
            // if the list is empty and no records found in the table
            Toast.makeText( this, "No Record Found...", Toast.LENGTH_SHORT ).show();
        }



        llistview.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                String tvt = ((TextView) view.findViewById(R.id.title2)).getText().toString();
                String tvd = ((TextView) view.findViewById(R.id.desc2)).getText().toString();
                String tvp = ((TextView) view.findViewById(R.id.phone2)).getText().toString();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.application);

                Intent intent1 = new Intent(Organization_advertisement.this,Views.class);
                intent1.putExtra("STRING_I_NEED_From_TVNAME", tvt );
                intent1.putExtra("STRING_I_NEED_From_TVdesc",tvd );
                intent1.putExtra("STRING_I_NEED_From_TVPHONE",tvp );
                startActivity(intent1);

              /* final CharSequence[] items = {"Update", "Delete","View"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(Organization_advertisement.this);

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
                            showupdatedialog(Organization_advertisement.this, attID.get(position));
                        }
                        if (i == 1) {
                            //delete

                            Cursor c = Organization_advertisement.mSqlitehelper.getData(" SELECT id FROM RECORD");
                            ArrayList<Integer> attID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                attID.add(c.getInt(0));
                            }
                            showdeletdialog(attID.get(position));
                        }
                    }
                });
                dialog.show();
*/

                return true;
            }

        });


    }

    private void viewData() {

        Cursor cursor = db.viewData();
        if (cursor.getCount()==0) {
            Toast.makeText(this, "No Data to show", Toast.LENGTH_SHORT).show();

        }else {

            while (cursor.moveToNext()) {

            }
            madapter = new OrganizationAdapter(this, R.layout.row_part, mlist);
            llistview.setAdapter(madapter);


            }



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
        mlist.clear();
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
            mlist.add( new Advertis( id, ti, de, ph, im ) );
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
                        Organization_advertisement.this, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        } );
        updatess.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                  Add_Advertisement.mSqlitehelper.updateData(
                            vtitle.getText().toString().trim(),
                            vdescrip.getText().toString().trim(),
                            vphone.getText().toString().trim(),
                            imageViewToByte( imagevicon ),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText( Organization_advertisement.this,"Update Successfull",Toast.LENGTH_SHORT ).show();
                }
                catch (Exception error){

                    Log.e( "Error Update",error.getMessage());
                }
                updateRecordList();
            }
        } );


    }

    private void showdeletdialog(final int idrecord) {

        AlertDialog.Builder deletedialog = new AlertDialog.Builder( Organization_advertisement.this );
        deletedialog.setTitle( " Warning!!" );
        deletedialog.setMessage( " Are you sure you want Delete?" );
        deletedialog.setPositiveButton( " OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                   Add_Advertisement.mSqlitehelper.deleteData(idrecord);
                    Toast.makeText(Organization_advertisement.this,"Deleted successfully",Toast.LENGTH_SHORT ).show();
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

        Cursor cursor = Add_Advertisement.mSqlitehelper.getData(  "SELECT * FROM RECORD");
        mlist.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt( 0 );
            String titles = cursor.getString( 1 );
            String describe = cursor.getString( 2 );
            String phones = cursor.getString( 3 );
            byte[] imgs = cursor.getBlob( 4 );
            //adds to list
            mlist.add( new Advertis( id, titles, describe, phones, imgs ) );
        }
        madapter.notifyDataSetChanged();
    }

    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        Drawable drawable = ((ImageView) image).getDrawable();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream );
        byte[] bytesArray = stream.toByteArray();
        return bytesArray;


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

    public void Viewing(View view) {

        Intent intent = new Intent(  Organization_advertisement.this,Views.class);
        intent.putExtra("Title",getTitle());
        startActivity( intent );
    }

    public void participateh(View view) {

        Intent parts = new Intent(  Organization_advertisement.this,Participate.class);
        startActivity( parts);
    }


    public void Recomendedh(View view) {

        Intent parts = new Intent(  Organization_advertisement.this,Recomended.class);
        startActivity( parts);
    }


}