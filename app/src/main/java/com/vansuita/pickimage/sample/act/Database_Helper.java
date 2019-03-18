package com.vansuita.pickimage.sample.act;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database_Helper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Contacts.db";
    private static final String TABLE_NAME = "Contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "uname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_CONFIRM = "confirm";


    private static final String databasename = "updates.db";
    private static final String TAble_NAme = "updates";
    private static final String COLUMN_Id = "id";
    private static final String COLUMN_NAMe = "username";
    private static final String COLUMN_Email = "emaiil";
    private static final String COLUMN_Phone = "phonee";
    private static final String COLUMN_Password = "userpassword";
    private static final String COLUMN_Pconfirm = "password";


    private static final String DB_name = "Advertisements.db";
    private static final String table_name = "Advertisements";
    private static final String column_id = "id";
    private static final String column_title = "title";
    private static final String column_descriptions = "description";
    private static final String column_phone = "phone";


    private static final String Contacts = " create table " + TABLE_NAME + "  ( id integer primary key not null," + "uname text not null," + " email text not null," + " phone integer not null," + " password text not null," + " confirm text not null );";

    private static final String updates = "create table " + TAble_NAme + "( id integer primary key not null," + "username text not null," + " emaiil text not null," + "phonee text not null," + "userpassword text not null," + "password text not null);";

    private static final String Advertisements = " create table " + table_name + "  ( id integer primary key not null," + "title text not null," + " description text not null," + " phone integer not null," + " category text not null );";


    Context context;
    SQLiteDatabase db;

    public Database_Helper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( Contacts );
        db.execSQL( updates );
        db.execSQL( Advertisements );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL( "DROP TABLE IF EXISTS " + TAble_NAme );
        db.execSQL( "DROP TABLE IF EXISTS " + table_name );
        onCreate( db );


    }


    public void insertContact(Contact c) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        String query = "select * from Contacts";
        Cursor cc = db.rawQuery( query, null );
        int count = cc.getCount();
        v.put( COLUMN_ID, count );
        v.put( COLUMN_NAME, c.getUname() );
        v.put( COLUMN_EMAIL, c.getEmail() );
        v.put( COLUMN_PHONE, c.getPhone() );
        v.put( COLUMN_PASSWORD, c.getPassword() );
        v.put( COLUMN_CONFIRM, c.getCpass() );

        db.insert( TABLE_NAME, null, v );

    }

    public void updates(String id, String Username, String Password, String Email, String Phone, String Conf) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from Contacts";
        Cursor cc = db.rawQuery( query, null );
        int count = cc.getCount();

        ContentValues u = new ContentValues();
        u.put( COLUMN_ID, count );
        u.put( "uname", Username );
        u.put( "email", Email );
        u.put( "phone", Phone );
        u.put( "password", Password );
        u.put( "confirm", Conf );
        db.update( TABLE_NAME, u, " id = ?", new String[]{id} );
    }

    public Cursor getAllrecords() {


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery( " select * from Contacts ", null );
        return res;

    }


    public String searchpass(String str) {
        db = this.getReadableDatabase();
        String query = "select uname, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery( query, null );
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString( 0 );
                if (a.equals( str )) {
                    b = cursor.getString( 1 );
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public void insertadvertisement(Advertis a) {
        db = this.getWritableDatabase();
        ContentValues adss = new ContentValues();
        String queryy = "select * from Advertisements";
        Cursor ca = db.rawQuery( queryy, null );
        int count = ca.getCount();
        adss.put( column_id, count );
        adss.put( column_title, a.getTitle() );
        adss.put( column_descriptions, a.getDecription() );
        adss.put( column_phone, a.getPhones() );

        db.insert( table_name, null, adss );

    }
    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_EMAIL+ " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public void updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( COLUMN_PASSWORD, password );
        db.update( TABLE_NAME, values, COLUMN_EMAIL + " = ?", new String[]{email} );
        db.close();
    }

    public Cursor viewData() {
        SQLiteDatabase db = getReadableDatabase();
        String query = " select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}