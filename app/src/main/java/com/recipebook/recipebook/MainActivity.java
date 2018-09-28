package com.recipebook.recipebook;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.recipebook.recipebook.db.Recipe;
import com.recipebook.recipebook.ui.HomeFragment;
import com.recipebook.recipebook.ui.RecipeDetailFragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    //public static RecipeRoomDatabase recipeRoomDatabase;
    //public RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        //initializing db, this will allow us to perform db transaction
        //recipeRoomDatabase = Room.databaseBuilder(getApplicationContext(), RecipeRoomDatabase
         //       .class, "recipedb").build();
      //  recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

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
                .fragment_container, new RecipeDetailFragment()).addToBackStack(null).commit();


    }

}
