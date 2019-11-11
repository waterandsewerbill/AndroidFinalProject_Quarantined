package com.example.androidfinalproject.currencyConverter;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CurrencyDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "CurrencyDB";
    static final int VERSION_NUM = 1;

    final static public String TABLE_FAVOURITE = "CurrencyFavourite";
    final static public String FILENAME = "CurrencyDBF";
    final static public String COL_BASE = "base";
    final static public String COL_RESULT = "result";
    final static public String COL_ID = "_id";

     public CurrencyDBHelper(Activity ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
     }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + TABLE_FAVOURITE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT," + COL_BASE + " text, "+ COL_RESULT +" integer);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database upgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);

        //Delete the old table:
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITE);

        //Create a new table:
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
