package com.recipebook.recipebooklet.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "RECIPES")
public class Recipe{

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String recipeTitle;
    private String category;
    private String serving;
    private String prepTime;
    private String cookingTime;
    private String ingredients;
    private String instructions;
    private String imagePath;

    public Recipe() {
    }

    @Ignore
    public Recipe(@NonNull String recipeTitle, String category, String serving, String prepTime, String cookingTime, String ingredients, String instructions, String imagePath) {
        this.recipeTitle = recipeTitle;
        this.category = category;
        this.serving = serving;
        this.prepTime = prepTime;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(@NonNull String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public static Recipe[] dataSample(){
        return new Recipe[]{
                new Recipe("Entree Test1","Entree","2","00:40","00:30", "Testing only 1/2 Cup " +
                        "Flour 200 ml " +
                        "milk", "Testing only Mix all ingredients together Step 2: put in " +
                        "oven over 180 " +
                        "degree", "entree.jpeg"),
                new Recipe("Entree Test2","Entree","2","00:40","00:30", "Testing only 1/2 Cup " +
                        "Flour 200 ml " +
                        "milk", "Testing only Mix all ingredients together Step 2: put in " +
                        "oven over 180 " +
                        "degree", "maincourse.jpeg"),
                new Recipe("Entree Test3","Entree","2","00:40","00:30", "Testing only 1/2 Cup " +
                        "Flour 200 ml " +
                        "milk", "Testing only Mix all ingredients together Step 2: put in " +
                        "oven over 180 " +
                        "degree", "drink.jpeg")
        };
    }
}
