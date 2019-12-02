package com.example.androidfinalproject.recipeFinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfinalproject.R;

/**
 * A class that is used to run the recipe fragment
 * @author Adam Matar
 * @version 1.0
 */
public class EmptyRecipeActivity extends AppCompatActivity {


    /**
     * onCreate method of the app life cycle
     * @author Adam Matar
     * @version 1.0
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_empty_activity);

        Bundle dataToPass = getIntent().getExtras(); //get the data that was passed from FragmentExample

        //This is copied directly from FragmentExample.java lines 47-54
        RecipeDetailFragment dFragment = new RecipeDetailFragment();
        dFragment.setArguments( dataToPass ); //pass data to the the fragment
        dFragment.setTablet(false); //tell the Fragment that it's on a phone.
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.recipeFragmentLocation, dFragment) // LAB PROF SAID USE REPLACE INSTEAD of Add
                .addToBackStack("AnyName")
                .commit();
    }

} // End of Activity

