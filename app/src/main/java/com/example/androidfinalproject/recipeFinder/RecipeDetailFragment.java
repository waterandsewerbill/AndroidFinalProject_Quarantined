package com.example.androidfinalproject.recipeFinder;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androidfinalproject.R;

/**
 * A class used to display a fragment
 * @author Adam Matar
 * @version 1.0
 */
public class RecipeDetailFragment extends Fragment{

    /**
     * A boolean representing if the device is a tablet
     */
    private boolean isTablet;

    /**
     * Bundle object with data from the previous activity
     */
    private Bundle dataFromActivity;

    /**
     * Integer representing an id
     */
    private long id;

    /**
     * Integer representing a position
     */
    private int position;

    /**
     * A method that sets the tablet boolean
     * @author Adam Matar
     * @version 1.0
     * @param tablet A boolean
     */
    public void setTablet(boolean tablet) { isTablet = tablet; }


    /**
     * A method that creates a view
     * @author Adam Matar
     * @version 1.0
     * @param inflater A layout inflater object
     * @param container A view group container object
     * @param savedInstanceState A Bundle object that stored data from the previous page
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dataFromActivity = getArguments();
        id = dataFromActivity.getLong(RecipeFinder.ITEM_ID );
        position = dataFromActivity.getInt(RecipeFinder.ITEM_POSITION);


        // Inflate the layout for this fragment
        View result =  inflater.inflate(R.layout.recipe_fragment_details, container, false);

        //show the message
        TextView titleView = (TextView)result.findViewById(R.id.recipeFragmentTitle);
        titleView.setText("Title: " + dataFromActivity.getString(RecipeFinder.ITEM_SELECTED));

        ImageView recipeFragmentImage = (ImageView)result.findViewById(R.id.recipeFragmentImage);
        //recipeFragmentImage.setImageBitmap();



        // get the delete button, and add a click listener:
        Button addButton = (Button)result.findViewById(R.id.recipeAddButton);
        addButton.setOnClickListener( clk -> {

            if(isTablet) { //both the list and details are on the screen:
                RecipeFinder parent = (RecipeFinder) getActivity();
                //parent.addRecipeId(id,position,); //this deletes the item and updates the list


                //now remove the fragment since you deleted it from the database:
                // this is the object to be removed, so remove(this):
                parent.getSupportFragmentManager().beginTransaction().remove(this).commit();
            }
            //for Phone:
            else //You are only looking at the details, you need to go back to the previous list page
            {
                EmptyRecipeActivity parent = (EmptyRecipeActivity) getActivity();
                Intent backToFragmentExample = new Intent();
                backToFragmentExample.putExtra(RecipeFinder.ITEM_ID, dataFromActivity.getLong(RecipeFinder.ITEM_ID ));

                parent.setResult(Activity.RESULT_OK, backToFragmentExample); //send data back to FragmentExample in onActivityResult()
                parent.finish(); //go back
            }
        });
        return result;
    }// End of onCreateView

} // End of Class
