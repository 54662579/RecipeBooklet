package com.recipebook.recipebooklet;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recipebook.recipebooklet.db.Recipe;
import com.recipebook.recipebooklet.ui.HomeFragment;
import com.recipebook.recipebooklet.ui.RecipeDetailFragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState !=null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    public void showDetail(Recipe recipe){
        RecipeDetailFragment recipeFragment = RecipeDetailFragment.forRecipe(recipe.getId());
        MainActivity.fragmentManager.beginTransaction().replace(R.id
                .fragment_container, recipeFragment).addToBackStack("recipe").commit();
    }

}
