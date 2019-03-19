package com.vansuita.pickimage.sample.act;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {


    SQLiteHelper(Context context,
                 String name,
                 SQLiteDatabase.CursorFactory factory,
                 int version) {

        super(context, name, factory, version);

    }

   public void querydata(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL( sql );
   }

//insert data
public void insertData(String title, String description, String phone, byte[] image, int category) {

        SQLiteDatabase database = this.getWritableDatabase();
        //query to insert data in db table

    String sql = "INSERT INTO RECORD VALUES(NULL,?,?,?,?,?)";
        //record is table name and created in main activity

        SQLiteStatement statement = database.compileStatement( sql );
        statement.clearBindings();

        statement.bindString( 1, title );
        statement.bindString( 2, description );
        statement.bindString( 3, phone );
        statement.bindBlob( 4, image );
    statement.bindString(5, String.valueOf(category));
        statement.executeInsert();

    }

    public void updateData(String title, String description, String phone,byte[]image,int id) {

        SQLiteDatabase database = this.getWritableDatabase();

        String sql = "UPDATE RECORD SET title =?, description=?, phone=?,image = ? WHERE id=?";

        SQLiteStatement statement = database.compileStatement( sql );

        statement.bindString( 1, title );
        statement.bindString( 2, description );
        statement.bindString( 3, phone );
        statement.bindBlob( 4, image );
        statement.bindDouble( 5, (double)id );

        statement.execute();
        database.close();
    }


    //deleting data
        public void deleteData(int id){

            SQLiteDatabase database = this.getWritableDatabase();

            String sql = "DELETE FROM RECORD WHERE id=?";
            SQLiteStatement statement = database.compileStatement( sql );
            statement.clearBindings();
            statement.bindDouble( 1,(double) id );

            statement.execute();
            database.close();
        }
        public Cursor getData(String sql){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery( sql,null );

        }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
