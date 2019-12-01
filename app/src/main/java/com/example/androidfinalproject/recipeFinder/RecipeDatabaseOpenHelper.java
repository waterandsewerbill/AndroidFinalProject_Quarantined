package com.example.androidfinalproject.recipeFinder;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecipeDatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String RECIPE_DATABASE_NAME = "RecipeDatabase";
    public static final int RECIPE_VERSION_NUM = 1;
    public static final String RECIPE_TABLE_NAME = "Recipes";

    public static final String COL_RECIPE_PULISHERNAME = "PUBLISHER";
    public static final String COL_RECIPE_F2F_URL = "F2F URL";
    public static final String COL_RECIPE_TITLE = "TITLE";
    public static final String COL_RECIPE_SOURCE_URL = "SOURCE URL";
    public static final String COL_RECIPE_ID = "ID";
    public static final String COL__RECIPE_SOCIAL_RANK = "SOCIAL RANK";
    public static final String COL_RECIPE_PUBLISHER_URL = "PUBLISHER URL";



    public RecipeDatabaseOpenHelper(Activity ctx){
        //The factory parameter should be null, unless you know a lot about Database Memory management
        super(ctx, RECIPE_DATABASE_NAME, null, RECIPE_VERSION_NUM );
    }

    public void onCreate(SQLiteDatabase db)
    {
        //Make sure you put spaces between SQL statements and Java strings:
        db.execSQL("CREATE TABLE " + RECIPE_TABLE_NAME + "( "
                + COL_RECIPE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_RECIPE_TITLE + " TEXT, " + COL_RECIPE_PULISHERNAME + " TEXT, " + COL__RECIPE_SOCIAL_RANK + "REAL)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("Database upgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);

        //Delete the old table:
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);

        //Create a new table:
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("Database downgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);

        //Delete the old table:
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);

        //Create a new table:
        onCreate(db);
    }


} // End of Class
