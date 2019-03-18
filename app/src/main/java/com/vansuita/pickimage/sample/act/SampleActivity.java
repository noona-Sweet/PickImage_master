package com.vansuita.pickimage.sample.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.vansuita.pickimage.sample.R;

import org.jetbrains.annotations.Nullable;

import androidx.appcompat.widget.Toolbar;

public class SampleActivity extends BaseSampleActivity implements IPickResult {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );


        Toolbar toool = (Toolbar) findViewById(R.id.toolbar);


    }

    @Override
    protected void onImageViewClick() {
        PickSetup setup = new PickSetup();

        super.customize(setup);

        PickImageDialog.build(setup)
                //.setOnClick(this)
                .show(this);

        //If you don't have an Activity, you can set the FragmentManager
        /*PickImageDialog.build(setup, new IPickResult() {
            @Override
            public void onPickResult(PickResult r) {
                r.getBitmap();
                r.getError();
                r.getUri();
            }
        }).show(getSupportFragmentManager());*/

        //For overriding the click events you can do this
        /*PickImageDialog.build(setup)
                .setOnClick(new IPickClick() {
                    @Override
                    public void onGalleryClick() {
                        Toast.makeText(SampleActivity.this, "Gallery Click!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCameraClick() {
                        Toast.makeText(SampleActivity.this, "Camera Click!", Toast.LENGTH_LONG).show();
                    }
                }).show(this);*/
    }

    @Override
    public void onPickResult(final PickResult r) {
        if (r.getError() == null) {
            //If you want the Uri.
            //Mandatory to refresh image from Uri.
            //getImageView().setImageURI(null);

            //Setting the real returned image.
            //getImageView().setImageURI(r.getUri());

            //If you want the Bitmap.
            getImageView().setImageBitmap(r.getBitmap());

            //r.getPath();
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }

        scrollToTop();
    }



    //If you use setOnClick(this), you have to implements this bellow methods
    /*@Override
    public void onGalleryClick() {
        Toast.makeText(SampleActivity.this, "Gallery Click!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCameraClick() {
        Toast.makeText(SampleActivity.this, "Camera Click!", Toast.LENGTH_LONG).show();
    }*/
}
