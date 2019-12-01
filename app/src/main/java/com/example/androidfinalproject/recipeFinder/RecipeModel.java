package com.example.androidfinalproject.recipeFinder;

import android.graphics.Bitmap;

public class RecipeModel {

    // Class Variables

    protected String recipePublisher; // Publisher name
    protected String recipe_f2f_url;
    protected String recipeTitle; // recipe title
    protected String recipe_source_url;
    protected String recipe_id; // JSON data has "bf5134 so I made it a string
    protected Bitmap recipe_icon; // Image icon
    protected String recipe_iconName; // Name of the image
    protected String recipe_iconFile; // Name + file extension .png
    protected double recipe_social_rank;
    protected String recipe_pulisher_url;


    // Methods

    public RecipeModel() {

    }//

    public RecipeModel(String recipeTitle, String recipe_id, String recipePublisher, double recipe_social_rank) {

        this.recipeTitle = recipeTitle;
        this.recipe_id = recipe_id;
        this.recipePublisher = recipePublisher;
        this.recipe_social_rank = recipe_social_rank;


    } // End of Constructor

    public String getRecipePublisher() {
        return recipePublisher;
    }

    public void setRecipePublisher(String recipePublisher) {
        this.recipePublisher = recipePublisher;
    }

    public String getRecipe_f2f_url() {
        return recipe_f2f_url;
    }

    public void setRecipe_f2f_url(String recipe_f2f_url) {
        this.recipe_f2f_url = recipe_f2f_url;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipe_source_url() {
        return recipe_source_url;
    }

    public void setRecipe_source_url(String recipe_source_url) {
        this.recipe_source_url = recipe_source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Bitmap getRecipe_icon() {
        return recipe_icon;
    }

    public void setRecipe_icon(Bitmap recipe_icon) {
        this.recipe_icon = recipe_icon;
    }

    public String getRecipe_iconName() {
        return recipe_iconName;
    }

    public void setRecipe_iconName(String recipe_iconName) {
        this.recipe_iconName = recipe_iconName;
    }

    public String getRecipe_iconFile() {
        return recipe_iconFile;
    }

    public void setRecipe_iconFile(String recipe_iconFile) {
        this.recipe_iconFile = recipe_iconFile;
    }

    public double getRecipe_social_rank() {
        return recipe_social_rank;
    }

    public void setRecipe_social_rank(double recipe_social_rank) {
        this.recipe_social_rank = recipe_social_rank;
    }

    public String getRecipe_pulisher_url() {
        return recipe_pulisher_url;
    }

    public void setRecipe_pulisher_url(String recipe_pulisher_url) {
        this.recipe_pulisher_url = recipe_pulisher_url;
    }

} // End of Class
