package com.example.androidfinalproject.recipeFinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.androidfinalproject.MainActivity;
import com.example.androidfinalproject.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * The main class for the recipe finder activity
 * @author Adam Matar
 * @verion 1.0
 */
public class RecipeFinder extends AppCompatActivity {

    // Class Variables

    /**
     * The activity name.
     */
    public static final String ACTIVITY_NAME = "RECIPE_FINDER";

    /**
     * The arraylist of recipes.
     */
    public ArrayList<RecipeModel> recipes = new ArrayList(); // Array List of Recipes

    /**
     * The arraylist of favorite recipes.
     */
    public ArrayList<RecipeModel> favorite_recipes = new ArrayList(); // List of favorite recipes

    /**
     * The base adapter used to make the list view.
     */
    BaseAdapter recipeAdapter;

    /**
     * The ListView
     */
    ListView recipeList;

    /**
     * The recipe search button.
     */
    Button recipeSearchButton;

    /**
     * The recipe search edit text box.
     */
    EditText recipeSearched;

    /**
     * The recipe to find.
     */
    String recipeToFind;

    /**
     * The recipe activity progress bar.
     */
    ProgressBar recipeProgressBar;

    /**
     * The recipe activity Toolbar
     */
    Toolbar recipeToolbar;

    /**
     * The shared preference object used.
     */
    SharedPreferences recipePref;

    /**
     * A text view that displays how many recipes found
     */
    TextView recipeCounterText;

    /**
     * Integer represesnting number of recipes found
     */
    int recipeCounter = 0;

    /**
     * Place holder string to used in database implementation.
     */
    public static final String ITEM_SELECTED = "ITEM";

    /**
     * Place holder string to used in database implementation.
     */
    public static final String ITEM_POSITION = "POSITION";

    /**
     * Place holder string to used in database implementation.
     */
    public static final String ITEM_ID = "ID";

    /**
     * Place holder int to used in database implementation.
     */
    public static final int EMPTY_RECIPE_ACTIVITY = 345;


    // URLS

    /**
     * URL used in Async execution
     */
    String Recipe_URL;

    /**
     * Chicken JSON URL
     */
    String chickenURL = "http://torunski.ca/FinalProjectChickenBreast.json";

    /**
     * Lasagna JSON URL
     */
    String lasagnaURL = "http://torunski.ca/FinalProjectLasagna.json";

    /**
     * onCreate method in the app life cycle.
     * @author Adam Matar
     * @version 1.0
     * @param savedInstanceState An object of type bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_finder);

        // Local Variables

        recipeList = findViewById(R.id.recipeList);
        recipeList.setAdapter(recipeAdapter = new RecipeListAdapter());
        recipeSearchButton = (Button) findViewById(R.id.recipeSearchButton);
        recipeSearched = (EditText) findViewById(R.id.recipeSearchBox);
        recipeProgressBar = (ProgressBar) findViewById(R.id.recipeProgressBar);
        recipeCounterText = (TextView) findViewById(R.id.recipeCounterText);
        TextView recipeFragmentTitle = (TextView) findViewById(R.id.recipeFragmentTitle);
        TextView recipeFragmentURL = (TextView) findViewById(R.id.recipeFragmentURL);
        ImageView recipeFragmentImage = (ImageView) findViewById(R.id.recipeFragmentImage);


        recipeToolbar = (Toolbar) findViewById(R.id.recipeToolbar);
        setSupportActionBar(recipeToolbar);

        // Shared Prefs
        recipePref = getSharedPreferences("Recipe", Context.MODE_PRIVATE);
        String recipeLastSearch = recipePref.getString("Reserved","");
        recipeSearched.setText(recipeLastSearch);



        recipeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                recipeCounterText.setText(""); // Clears
                recipes.clear(); // Clears the Array List

                recipeToFind = recipeSearched.getText().toString();


                switch(recipeToFind.toLowerCase()){

                    case "chicken breast":
                        Recipe_URL = chickenURL;
                        break;

                    case "lasagna":
                        Recipe_URL = lasagnaURL;
                        break;

                    default:
                        Recipe_URL = null;
                        recipeAlertSearch();
                        recipes.clear(); // Empty Array
                        break;
                } // End of SWITCH

                RecipeQuery query = new RecipeQuery(); // Creates query object
                query.execute(Recipe_URL); // Gets the API INFO

                recipeAdapter.notifyDataSetChanged();

            }
        });

        boolean isTablet = findViewById(R.id.recipeFragmentLocation) != null; //check if the FrameLayout is loaded

        recipeList.setOnItemClickListener( (list, item, position, id) -> {

            Bundle dataToPass = new Bundle();
            dataToPass.putString(ITEM_SELECTED, recipes.get(position).getRecipeTitle());
            dataToPass.putInt(ITEM_POSITION, position);
            dataToPass.putLong(ITEM_ID, recipes.get(position).getRecipeDataBaseID());

            if (isTablet) {
                RecipeDetailFragment dFragment = new RecipeDetailFragment(); //add a DetailFragment
                dFragment.setArguments(dataToPass); //pass it a bundle for information
                dFragment.setTablet(true);  //tell the fragment if it's running on a tablet or not
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.recipeFragmentLocation, dFragment) //Add the fragment in FrameLayout
                        .addToBackStack("AnyName") //make the back button undo the transaction
                        .commit(); //actually load the fragment.
            } else //isPhone
            {
                Intent nextActivity = new Intent(RecipeFinder.this, EmptyRecipeActivity.class);
                nextActivity.putExtras(dataToPass); //send data to next activity
                startActivityForResult(nextActivity, EMPTY_RECIPE_ACTIVITY); //make the transition
            }
        });

    } // End of onCreate()

    /**
     * onResume method in the app life cycle.
     * @author Adam Matar
     * @version 1.0
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In Function onResume()");

        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();

    } // End of onResume

    /**
     * onDestroy method in the app life cycle.
     * @author Adam Matar
     * @version 1.0
     */
    @Override
    protected void onDestroy() {
        Log.e(ACTIVITY_NAME, "In Function onDestroy()");
        super.onDestroy();
    }

    /**
     * onStart method in the app life cycle.
     * @author Adam Matar
     *      * @version 1.0
     */
    @Override
    protected void onStart() {
        Log.e(ACTIVITY_NAME, "In Function onStart()");
        super.onStart();
    }

    /**
     * onPause method in the app life cycle.
     * @author Adam Matar
     * @version 1.0
     */
    @Override
    protected void onPause() {
        Log.e(ACTIVITY_NAME, "In Function onPause()");
        super.onPause();

        SharedPreferences.Editor recipeEdit = recipePref.edit();
        recipeEdit.putString("Reserved", recipeSearched.getText().toString());
        recipeEdit.commit();

    } // End of onPause()

    /**
     * onStop method in the app life cycle.
     * @author Adam Matar
     * @version 1.0
     */
    @Override
    protected void onStop() {
        Log.e(ACTIVITY_NAME, "In Function onStop()");
        super.onStop();

    }

    /**
     * A private inner class used to get the data from the API URLS
     * @author Adam Matar
     * @version 1.0
     */
    private class RecipeQuery extends AsyncTask<String,Integer,String> {

        // Local Variables

        protected String recipeTitle;
        protected long recipeID;
        protected String recipePublisher;
        protected double recipeSocialRank;
        protected int recipeCount;


        @Override
        protected String doInBackground(String... strings) {

            String ret = null;
            Bitmap icon;
            String iconName;
            String iconFile;

            try {

                URL recipe_url_1 = new URL(Recipe_URL);
                HttpURLConnection connectionURL = (HttpURLConnection) recipe_url_1.openConnection();
                InputStream inStream = connectionURL.getInputStream();


                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                String result = sb.toString();

                JSONObject jObject = new JSONObject(result); // Gets the JSON count object
                recipeCount = jObject.getInt("count"); // Gets the Count
                recipeCounter = recipeCount;


                JSONArray recipeArray = jObject.getJSONArray("recipes"); // Creates JSON ARRAY

                for (int i = 0; i < recipeCount; i++){

                    try{

                        JSONObject aRecipe = recipeArray.getJSONObject(i); // Gets the object.
                        RecipeModel newRecipe = new RecipeModel();

                        newRecipe.setRecipePublisher(aRecipe.getString("publisher"));
                        publishProgress(10);
                        newRecipe.setRecipe_f2f_url(aRecipe.getString("f2f_url"));
                        publishProgress(20);
                        newRecipe.setRecipeTitle(aRecipe.getString("title"));
                        publishProgress(30);
                        newRecipe.setRecipe_source_url(aRecipe.getString("source_url"));
                        publishProgress(40);
                        newRecipe.setRecipe_id(aRecipe.getString("recipe_id"));
                        publishProgress(50);
                        newRecipe.setRecipe_image_url(aRecipe.getString("image_url"));
                        newRecipe.setRecipe_iconFile(newRecipe.getRecipe_image_url().substring(28)); // Gets image file name
                        publishProgress(60);
                        newRecipe.setRecipe_social_rank(aRecipe.getDouble("social_rank"));
                        publishProgress(70);
                        newRecipe.setRecipe_pulisher_url(aRecipe.getString("publisher_url"));
                        newRecipe.setRecipeDataBaseID(i);




                        if (recipeFileExistance(newRecipe.getRecipe_iconFile())) {

                            // LOAD IF IT EXISTS

                            FileInputStream fis = null;
                            try {
                                fis = openFileInput(newRecipe.getRecipe_iconFile());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            newRecipe.setRecipe_icon(BitmapFactory.decodeStream(fis));

                            Log.i("File found ", "file name = " + newRecipe.getRecipe_iconFile());

                        } // End of if File Exists
                        else {

                            Log.i("Not Found DOWNLOADING", "file name= " + newRecipe.getRecipe_iconFile());

                            URL recipe_url_2 = new URL(newRecipe.getRecipe_image_url());
                            HttpURLConnection connection = (HttpURLConnection) recipe_url_2.openConnection();
                            connection.connect();
                            int responseCode = connection.getResponseCode();
                            if (responseCode == 200) {
                                newRecipe.setRecipe_icon(BitmapFactory.decodeStream(connection.getInputStream()));


                                FileOutputStream outputStream = openFileOutput(newRecipe.getRecipe_iconFile(), Context.MODE_PRIVATE);
                                newRecipe.getRecipe_icon().compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                                outputStream.flush();
                                outputStream.close();
                            }


                        } // End of Else doesn't exist

                        Log.i("File downloaded ", "file name= " + newRecipe.getRecipe_iconFile());

                        //recipes.add(newRecipe); // Adds Recipe to Array

                        recipes.add(newRecipe); // Adds Recipe to Array


                    } // End of TRY

                    catch (JSONException e) {
                        e.printStackTrace();
                    } // End of CATCH

                } // End of FOR LOOP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            publishProgress(100);

            //What is returned here will be passed as a parameter to onPostExecute:
            return ret;

        } // End of doInBackGround()

        protected void onProgressUpdate(Integer... values) { // UPDATE PROGRESS BAR
            super.onProgressUpdate(values);

            recipeProgressBar.setVisibility(View.VISIBLE);
            recipeProgressBar.setProgress(values[0]);

        } // End of onProgressUpdate()

        @Override
        protected void onPostExecute(String s) { // UPDATE THE GUI
            super.onPostExecute(s);
            recipeCounterText.setText("Found: " + recipeCount);
            recipeProgressBar.setVisibility(View.INVISIBLE);


            //recipeFragmentImage.setImageBitmap(icon);


        } // End of onPostExecute()

    } // End of INNER PRIVATE CLASS


    /**
     * A private inner class used to create an adapter to adapt the list view
     * @author Adam Matar
     * @version 1.0
     */
    private class RecipeListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return recipes.size(); // GETS THE SIZE
        }

        @Override
        public RecipeModel getItem(int i) {
            return recipes.get(i); // Gets the OBJECT
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View recipeView, ViewGroup parent) {

            // Local Variables

            View thisRow;

            LayoutInflater inflater = RecipeFinder.this.getLayoutInflater();

            thisRow = inflater.inflate(R.layout.recipe_layout,null); // Inflates the view

            TextView recipeTitle = thisRow.findViewById(R.id.recipeTitle);
            //ImageView recipeImage = thisRow.findViewById(R.id.recipeTestImage);

            recipeTitle.setText(recipes.get(i).getRecipeTitle());
            //recipeImage.setImageBitmap(recipes.get(i).getRecipe_icon());


            return thisRow;

        } // End of getView


    } // End of PRIVATE INNER CLASS

    /**
     * Method used to inflate a menu onto the toolbar
     * @author Adam Matar
     * @version 1.0
     * @param menu An object of type menu
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);

        return true;
    }

    /**
     * A method that performs an event when an item in the list is clicked
     * @author Adam Matar
     * @version 1.0
     * @param item An object of type menu
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            //what to do when the menu item is selected:
            case R.id.recipe_item1:
                Toast.makeText(this, "Click on the star icon to access your favorites", Toast.LENGTH_LONG).show();
                break;

            case R.id.recipe_item2:

                // ACCESS FAVORITES LIST

                Toast.makeText(this, "Not implemented.", Toast.LENGTH_LONG).show();
                break;


            case R.id.recipe_item3:
               recipeAlertHelp();
                break;

            case R.id.recipe_item4:
                Snackbar snack = Snackbar.make(recipeToolbar, "Are you sure you want to quit?", Snackbar.LENGTH_LONG)
                        .setAction("Quit?", e -> finish());
                snack.show();
                break;
        }
        return true;
    }

    /**
     * A method that invokes a custom dialog
     * @author Adam Matar
     * @version 1.0
     */
    public void recipeAlertHelp() {

        View box = getLayoutInflater().inflate(R.layout.recipe_help_dialog, null);


        //EditText et = (EditText)box.findViewById(R.id.recipeDialogEditText);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The Message")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //overflowString = et.getText().toString();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What to do on Cancel
                    }
                }).setView(box);

        builder.create().show();


    } // End of recipeAlertHelp


    /**
     * A method that invokes a custom dialog
     * @author Adam Matar
     * @version 1.0
     */
    public void recipeAlertSearch() {

        View box = getLayoutInflater().inflate(R.layout.recipe_search_dialog, null);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The Message")
                .setPositiveButton("Try again?", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        recipeCounterText.setText("Found: " + 0);
                        recipeSearched.setText(""); // Reset
                        recipes.clear(); // Clears the Array List
                        recipeAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                }).setView(box);

        builder.create().show();


    } // End of recipeAlertSearch()

    /**
     * A method that checks if a file exists
     * @author Adam Matar
     * @version 1.0
     * @param fname A string representing file name
     * @return A boolean
     */
    public boolean recipeFileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();

    } // End of recipeFileExistance

    //This function only gets called on the phone. The tablet never goes to a new activity

    /**
     * A method that does something depending on the result returned from the activity
     * @author Adam Matar
     * @version 1.0
     * @param requestCode The request code
     * @param resultCode The result code
     * @param data The data from the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EMPTY_RECIPE_ACTIVITY)
        {
            if(resultCode == RESULT_OK) //if you hit the delete button instead of back button
            {
                long id = data.getLongExtra(ITEM_ID, 0);
                int position = data.getIntExtra(ITEM_POSITION, 0);
                deleteRecipeId(id, position);
            }
        }
    }

    /**
     * A method for deleting recipes from the database
     * @author Adam Matar
     * @version 1.0
     * @param id An integer representing an id
     * @param position An integer representing a position
     */
    public void deleteRecipeId(long id, int position) // DELETE RECIPE FUNCTION
    {
        Log.i("Delete this recipe:" , " id="+id);
        // use delete query, so you can delete the item using the id from the darabase
        favorite_recipes.remove(position);
        recipeAdapter.notifyDataSetChanged();
    }


} // End of RecipeFinder Class

