package com.example.androidfinalproject.recipeFinder;

import android.graphics.Bitmap;

/**
 * A class representing a recipe object
 * @author Adam Matar
 * @version 1.0
 */
public class RecipeModel {

    // Class Variables

    /**
     * A long representing an id
     */
    protected long recipeDataBaseID;

    /**
     * A string representing a publisher name
     */
    protected String recipePublisher; // Publisher name

    /**
     * A string representing a f2f url
     */
    protected String recipe_f2f_url;

    /**
     * A string representing a recipe title
     */
    protected String recipeTitle; // recipe title

    /**
     * A string representing a source url
     */
    protected String recipe_source_url;

    /**
     * A string representing the retrieve recipe id
     */
    protected String recipe_id; // JSON data has "bf5134 so I made it a string

    /**
     * A Bitmap object that holds the recipe image
     */
    protected Bitmap recipe_icon; // Image icon

    /**
     * A string representing a recipe image name without extension
     */
    protected String recipe_iconName; // Name of the image

    /**
     * A string representing a picture file. Name + extension
     */
    protected String recipe_iconFile; // Name + file extension .png

    /**
     * A string representing an image url
     */
    protected String recipe_image_url;

    /**
     * A double representing a social rank
     */
    protected double recipe_social_rank;

    /**
     * A string representing a recipe publisher url
     */
    protected String recipe_pulisher_url;


    // Methods

    /**
     * The recipe object constructor
     * @author Adam Matar
     * @version 1.0
     * @param recipeTitle A string representing a recipe title
     * @param recipe_id A string representing a recipe id
     * @param recipePublisher A string representing a publisher name
     * @param recipe_social_rank A double representing a recipe social rank
     */
    public RecipeModel(String recipeTitle, String recipe_id, String recipePublisher, double recipe_social_rank) {

        this.recipeTitle = recipeTitle;
        this.recipe_id = recipe_id;
        this.recipePublisher = recipePublisher;
        this.recipe_social_rank = recipe_social_rank;


    } // End of Constructor

    /**
     * Default constructor
     * @author Adam Matar
     * @version 1.0
     */
    public RecipeModel() {

    }

    /**
     * Gets the publisher name of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @return The publisher name of the recipe object
     */
    public String getRecipePublisher() {
        return recipePublisher;
    }

    /**
     * Sets the publisher name of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @param recipePublisher A string representing a recipe publisher's name
     */
    public void setRecipePublisher(String recipePublisher) {
        this.recipePublisher = recipePublisher;
    }

    /**
     * Gets the f2f url of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @return The f2f url of the recipe object
     */
    public String getRecipe_f2f_url() {
        return recipe_f2f_url;
    }

    /**
     * Sets the f2f url of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @param recipe_f2f_url A string representing an f2f url
     */
    public void setRecipe_f2f_url(String recipe_f2f_url) {
        this.recipe_f2f_url = recipe_f2f_url;
    }

    /**
     * Gets the recipe title of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @return The recipe title of the recipe object
     */
    public String getRecipeTitle() {
        return recipeTitle;
    }

    /**
     * Sets the recipe title of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @param recipeTitle A string representing a recipe title.
     */
    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    /**
     * Gets the recipe source url of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @return The recipe source url of the recipe object
     */
    public String getRecipe_source_url() {
        return recipe_source_url;
    }

    /**
     * Sets the recipe source url of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @param recipe_source_url A string representing a recipe source url
     */
    public void setRecipe_source_url(String recipe_source_url) {
        this.recipe_source_url = recipe_source_url;
    }

    /**
     * Gets the recipe id of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @return The recipe id of the recipe object
     */
    public String getRecipe_id() {
        return recipe_id;
    }

    /**
     * Sets the recipe id of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @param recipe_id A string representing the id of the recipe
     */
    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    /**
     * Gets the icon of the recipe object
     * @author Adam Matar
     * @version 1.0
     * @return The icon of the recipe object
     */
    public Bitmap getRecipe_icon() {
        return recipe_icon;
    }

    /**
     * Sets the image icon of the recipe
     * @author Adam Matar
     * @version 1.0
     * @param recipe_icon A bitmap object representing the image icon
     */
    public void setRecipe_icon(Bitmap recipe_icon) {
        this.recipe_icon = recipe_icon;
    }

    /**
     * Gets the icon image name
     * @author Adam Matar
     * @version 1.0
     * @return A string representing the icon image name
     */
    public String getRecipe_iconName() {
        return recipe_iconName;
    }

    /**
     * Sets the icon image name
     * @author Adam Matar
     * @version 1.0
     * @param recipe_iconName A string representing the icon image name
     */
    public void setRecipe_iconName(String recipe_iconName) {
        this.recipe_iconName = recipe_iconName;
    }

    /**
     * Gets the recipe image file name.
     * @author Adam Matar
     * @version 1.0
     * @return A string representing the image file name
     */
    public String getRecipe_iconFile() {
        return recipe_iconFile;
    }

    /**
     * Sets the image file name.
     * @author Adam Matar
     * @version 1.0
     * @param recipe_iconFile A string represnting the image file name
     */
    public void setRecipe_iconFile(String recipe_iconFile) {
        this.recipe_iconFile = recipe_iconFile;
    }

    /**
     * Gets the recipe social rank
     * @author Adam Matar
     * @version 1.0
     * @return A string representing the recipe social rank
     */
    public double getRecipe_social_rank() {
        return recipe_social_rank;
    }

    /**
     * Sets the recipe social rank
     * @author Adam Matar
     * @version 1.0
     * @param recipe_social_rank A double representing the recipe social rank
     */
    public void setRecipe_social_rank(double recipe_social_rank) {
        this.recipe_social_rank = recipe_social_rank;
    }

    /**
     * Gets the recipe publisher url
     * @author Adam Matar
     * @version 1.0
     * @return A string representing the publisher url
     */
    public String getRecipe_pulisher_url() {
        return recipe_pulisher_url;
    }

    /**
     * Sets the publisher url
     * @author Adam Matar
     * @version 1.0
     * @param recipe_pulisher_url A string representing the publisher url
     */
    public void setRecipe_pulisher_url(String recipe_pulisher_url) {
        this.recipe_pulisher_url = recipe_pulisher_url;
    }

    /**
     * Gets the image URL
     * @author Adam Matar
     * @version 1.0
     * @return A string representing the image url
     */
    public String getRecipe_image_url() {
        return recipe_image_url;
    }

    /**
     * Sets the image URL
     * @author Adam Matar
     * @version 1.0
     * @param recipe_image_url A string representing the image url
     */
    public void setRecipe_image_url(String recipe_image_url) {
        this.recipe_image_url = recipe_image_url;
    }

    /**
     * Gets the recipe database id
     * @author Adam Matar
     * @version 1.0
     * @return A long representing the data base id of the recipe
     */
    public long getRecipeDataBaseID() {
        return recipeDataBaseID;
    }

    /**
     * Sets the recipe database id
     * @author Adam Matar
     * @version 1.0
     * @param recipeDataBaseID A long representing the database id
     */
    public void setRecipeDataBaseID(long recipeDataBaseID) {
        this.recipeDataBaseID = recipeDataBaseID;
    }


} // End of Class
