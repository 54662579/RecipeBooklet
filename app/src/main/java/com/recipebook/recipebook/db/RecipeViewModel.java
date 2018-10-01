package com.recipebook.recipebook.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> recipeList;
    private int mRecipeId;


    public RecipeViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        recipeList = recipeRepository.getRecipeList();
    }

    public LiveData<List<Recipe>> getRecipeList(){
        if (recipeList == null){
            recipeList = recipeRepository.getRecipeList();
        }
        return recipeList;
    }

    public LiveData<List<Recipe>> getRecipeByCategory(String category){
        return recipeRepository.getRecipeByCategory(category);
    }

    public void insertRecipe(Recipe recipe){
        recipeRepository.insertRecipe(recipe);
    }

    public Recipe getRecipeById(int recipeId){
        return recipeRepository.getRecipeById(recipeId);
    }
}
