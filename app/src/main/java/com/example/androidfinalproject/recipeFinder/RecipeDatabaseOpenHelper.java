package com.example.androidfinalproject.recipeFinder;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class used to open a database
 * @author Adam Matar
 */
public class RecipeDatabaseOpenHelper extends SQLiteOpenHelper {

    /**
     * String representing the database name.
     */
    public static final String RECIPE_DATABASE_NAME = "RecipeDatabase";

    /**
     * An integer representing the data base version number.
     */
    public static final int RECIPE_VERSION_NUM = 1;

    /**
     * A string representing the table name.
     */
    public static final String RECIPE_TABLE_NAME = "Recipes";

    /**
     * A string represnting the publisher name
     */
    public static final String COL_RECIPE_PULISHERNAME = "PUBLISHER";

    /**
     * A string representing the f2f url
     */
    public static final String COL_RECIPE_F2F_URL = "F2F URL";

    /**
     * A string representing the recipe title
     */
    public static final String COL_RECIPE_TITLE = "TITLE";

    /**
     * A string representing the recipe source url
     */
    public static final String COL_RECIPE_SOURCE_URL = "SOURCE URL";

    /**
     * A string representing the recipe ID.
     */
    public static final String COL_RECIPE_ID = "ID";

    /**
     * A string representing the social rank
     */
    public static final String COL__RECIPE_SOCIAL_RANK = "SOCIAL RANK";

    /**
     * A string representing the publisher url
     */
    public static final String COL_RECIPE_PUBLISHER_URL = "PUBLISHER URL";


    /**
     * Method that creates database open helper
     * @author Adam Matar
     * @param ctx An activity object
     */
    public RecipeDatabaseOpenHelper(Activity ctx){
        //The factory parameter should be null, unless you know a lot about Database Memory management
        super(ctx, RECIPE_DATABASE_NAME, null, RECIPE_VERSION_NUM );
    }

    /**
     * onCreate method of the app life cycle
     * @author Adam Matar
     * @param db An SQLiteDatabase object
     */
    public void onCreate(SQLiteDatabase db)
    {
        //Make sure you put spaces between SQL statements and Java strings:
        db.execSQL("CREATE TABLE " + RECIPE_TABLE_NAME + "( "
                + COL_RECIPE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_RECIPE_TITLE + " TEXT, " + COL_RECIPE_PULISHERNAME + " TEXT, " + COL__RECIPE_SOCIAL_RANK + "REAL)");
    }

    /**
     * A method that upgrades the database
     * @author Adam Matar
     * @param db An SQLiteDatabase object
     * @param oldVersion Integer representing the old version
     * @param newVersion Integer representing the new version
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("Database upgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);

        //Delete the old table:
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);

        //Create a new table:
        onCreate(db);
    }

    /**
     * A method that downgrades the database
     * @author Adam Matar
     * @param db An SQLiteDatabase object
     * @param oldVersion Integer representing the old version
     * @param newVersion Integer representing the new version
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i("Database downgrade", "Old version:" + oldVersion + " newVersion:"+newVersion);

        //Delete the old table:
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);

        //Create a new table:
        onCreate(db);
    }


} // End of Class
