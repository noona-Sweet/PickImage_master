package com.vansuita.pickimage.sample.act;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {

    private final static String SHARED_FILE = "_dataFile";

    protected static final String KEY = "user-session";

    private SharedPreferences pref ;

    public static final String U_ID = "u_Id";
    public static final String USER_TYPE = "u_type";
    public static final String PARTICIPATES_COUNTER = "participates_counter";


    public AppSharedPreferences(Context context) {
        pref = context.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
    }

    public void writeString(String key, String value) {

        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putString(key, value);
        prefEditor.apply();

    }

    public String readString(String key) {
        return pref.getString(key, "");
    }

    public void writeInteger(String key, int value) {

        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putInt(key, value);
        prefEditor.apply();

    }

    public int readInteger(String key) {
        return pref.getInt(key, 0);
    }

    public void writeBoolean(String key, boolean value) {

        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putBoolean(key, value);
        prefEditor.apply();

    }

    public boolean readBoolean(String key) {
        return pref.getBoolean(key, false);
    }

    public void defualtData () {
        SharedPreferences.Editor prefEditor = pref.edit();
        ///////////////////////////////////////////
/*      prefEditor.putString("token",null);
        prefEditor.putString("refresh_token",null);*/
        ///////////////////////////////////////////
        prefEditor.apply();
    }


    public void clear() {
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.clear();
        prefEditor.apply();

    }
}
