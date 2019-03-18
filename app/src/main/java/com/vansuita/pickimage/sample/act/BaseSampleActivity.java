package com.vansuita.pickimage.sample.act;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.sample.R;

import org.jetbrains.annotations.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public abstract class BaseSampleActivity extends AppCompatActivity {

    private ImageView imageView;
    private ScrollView scrollView;
    private SharedPreferences prefs;

    Database_Helper mydb = new Database_Helper(this);
    EditText ID,etuser, etmail,etphone,etpass,etcpass;
    String ii,Username ,Email, Phone,Password, Conf;
    Button saave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);


        Toolbar toool = (Toolbar) findViewById(R.id.toolbar);



        scrollView = ((ScrollView) findViewById(R.id.scroll));
        imageView = ((ImageView) findViewById(R.id.result_image));

        ID = (EditText)findViewById( R.id.Id );
        etuser = (EditText) findViewById( R.id.usernamee );
        etmail = (EditText) findViewById( R.id.email );
        etphone = (EditText) findViewById( R.id.phonee );
        etpass = (EditText) findViewById( R.id.pass );
        etcpass = (EditText) findViewById( R.id.cpass );
        saave = (Button)findViewById( R.id.bttnedite );


        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        getFragmentManager().beginTransaction().replace(R.id.prefs_holder, new SampleActivity.InnerPreferenceFragment()).commit();

        initialize();
        updatedata();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_volunteer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.msg) {
            Intent messaage = new Intent(BaseSampleActivity.this, Messages.class);
            startActivity(messaage);
        }
        if (id == R.id.homee) {
            Intent hpp = new Intent(BaseSampleActivity.this, Voulnteers.class);
            startActivity(hpp);
        }
        if (id == R.id.options) {
            Toast.makeText(BaseSampleActivity.this, "choose Option", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.edits) {
            Intent edit = new Intent(BaseSampleActivity.this, SampleActivity.class);
            startActivity(edit);


        }
        if(id == R.id.hs) {
            Intent help = new Intent(BaseSampleActivity.this, Help_Support.class);
            startActivity(help);
        }
        if (id == R.id.lo) {
            Intent loog = new Intent(BaseSampleActivity.this, Welcome.class);
            startActivity(loog);
        }
        return true;
    }

     public void updatedata(){

        saave.setOnClickListener( new View.OnClickListener() {
      @Override
           public void onClick(View v) {
          ii = ID.getText().toString().trim();
        Username = etuser.getText().toString().trim();
        Email = etmail.getText().toString().trim();
        Phone= etphone.getText().toString().trim();
        Password = etpass.getText().toString().trim();
        Conf= etcpass.getText().toString().trim();

        mydb.updates(ii, Username,Password, Email,Phone, Conf );
          Cursor cursor = mydb.getAllrecords();

        if (cursor.getCount()> 0) {
            Toast.makeText( BaseSampleActivity.this, "Update", Toast.LENGTH_SHORT ).show();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String ti = cursor.getString(1);
                etuser.setText(ti);
                String de = cursor.getString(2);
                etmail.setText(de);
                String ph = cursor.getString(3);
                etphone.setText(ph);
                String phh = cursor.getString(4);
                etpass.setText(phh);
                String h = cursor.getString(5);
                etcpass.setText(h);

                mydb.updates(ii, Username, Password, Email, Phone, Conf);
                Intent intent = new Intent(BaseSampleActivity.this, Voulnteers.class);
                startActivity(intent);

            }

            if (!Password.equals(Conf)) {
            Toast.makeText( BaseSampleActivity.this, "Passwords didn't match", Toast.LENGTH_SHORT ).show();
        }else if (cursor.getCount()== 0){
          Toast.makeText( BaseSampleActivity.this, "No Data", Toast.LENGTH_SHORT ).show();

        } }
        }

        });
    }




    public static class InnerPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.prefs);
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            UI.setSummaryForGroups((PreferenceGroup) findPreference(getString(R.string.key_text_preference_group)));
            UI.setSummaryForGroups((PreferenceGroup) findPreference(getString(R.string.key_additional_preference_group)));

            UI.setDynamicHeight((ListView) getView().findViewById(android.R.id.list));
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            UI.setSummary(sharedPreferences, findPreference(key));
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        scrollToTop();
    }
    public void scrollToTop() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                scrollView.fullScroll(scrollView.FOCUS_UP);
            }
        }, 50);

        /*scrollView.post(new Runnable() {
            public void run() {
                scrollView.fullScroll(scrollView.FOCUS_UP);
            }
        });*/
    }

    protected void initialize() {
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageView.setImageResource(R.mipmap.default_image);
                return true;
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onImageViewClick();
            }
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    protected abstract void onImageViewClick();

    protected String getStr(int key) {
        return prefs.getString(getString(key), "");
    }

    protected int getInt(int key) {
        return prefs.getInt(getString(key), 0);
    }

    protected float getFloat(int key) {
        return Float.parseFloat(prefs.getString(getString(key), "0"));
    }

    protected int getNum(int key) {
        return Integer.parseInt(prefs.getString(getString(key), "0"));
    }

    protected boolean getBool(int key) {
        return prefs.getBoolean(getString(key), false);
    }

    protected void customize(PickSetup setup) {
        setup.setTitle(getStr(R.string.key_dialog_title));
        setup.setTitleColor(getInt(R.string.key_title_color));

        setup.setBackgroundColor(getInt(R.string.key_background_color));

        setup.setProgressText(getStr(R.string.key_progress_text));
        setup.setProgressTextColor(getInt(R.string.key_progress_text_color));

        setup.setCancelText(getStr(R.string.key_cancel_text));
        setup.setCancelTextColor(getInt(R.string.key_cancel_text_color));

        setup.setButtonTextColor(getInt(R.string.key_button_text_color));

        setup.setDimAmount(getFloat(R.string.key_dim_amount));
        setup.setFlip(getBool(R.string.key_flip_image));
        setup.setMaxSize(getNum(R.string.key_max_image_size));

        setup.setPickTypes(EPickType.fromInt(getNum(R.string.key_providers)));

        setup.setCameraButtonText(getStr(R.string.key_camera_button_text));
        setup.setGalleryButtonText(getStr(R.string.key_gallery_button_text));
        setup.setIconGravityInt(getNum(R.string.key_icon_gravity));

        setup.setButtonOrientationInt(getNum(R.string.key_buttons_orientation));
        setup.setSystemDialog(getBool(R.string.key_system_dialog));

        //setup.setWidth(800).setHeight(700);
        //setup.setVideo(true);

        if (getBool(R.string.key_colored_icons)) {
            setup.setGalleryIcon(R.mipmap.gallery_colored);
            setup.setCameraIcon(R.mipmap.camera_colored);
        }
    }
}
