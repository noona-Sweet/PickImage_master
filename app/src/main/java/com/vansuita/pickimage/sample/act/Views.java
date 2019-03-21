package com.vansuita.pickimage.sample.act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vansuita.pickimage.sample.R;

public class Views extends AppCompatActivity {
    TextView viiews,viewsa,viewsb;
    ImageView viewBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_views );

       Toolbar toool = findViewById(R.id.toolbar);

         viewBitmap = (ImageView)findViewById(R.id.imageView3);


        Bundle extras = getIntent().getExtras();
        String VALUE_1= extras.getString("STRING_I_NEED_From_TVNAME");
        String Value_2 = extras.getString("STRING_I_NEED_From_TVdesc");
        String Value_3 = extras.getString("STRING_I_NEED_From_TVPHONE");
        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
        viewBitmap.setImageBitmap(bitmap);
         viiews=(TextView)findViewById(R.id.txtviews);
        viiews.setText("Title : \n" + VALUE_1+"  \n\n"+"Description : \n"+ Value_2+ "\n\n" +"Phone Num :\n " + Value_3 );


    }
}
