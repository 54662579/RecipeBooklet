package com.recipebook.recipebook.ui;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.recipebook.recipebook.MainActivity;
import com.recipebook.recipebook.R;
import com.recipebook.recipebook.db.Recipe;
import com.recipebook.recipebook.db.RecipeViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecipeFragment extends android.support.v4.app.Fragment {

    private EditText rTitle, rServing, rPrepTime, rCookingTime, rIngredients,
            rInstrucitons;
    private Spinner rCategory;
    private Button saveButton;
    private RecipeViewModel recipeViewModel;


    public AddRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_recipe, container, false);
        rTitle = view.findViewById(R.id.editRecipeTitle);
        rCategory = view.findViewById(R.id.recipeCategory);
        rServing = view.findViewById(R.id.editServing);
        rPrepTime = view.findViewById(R.id.editPrepTime);
        rCookingTime = view.findViewById(R.id.editCookingTime);
        rIngredients = view.findViewById(R.id.editIngredients);
        rInstrucitons = view.findViewById(R.id.editInstructions);
        saveButton = view.findViewById(R.id.saveButton);


        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String title = rTitle.getText().toString();
                String category = rCategory.getSelectedItem().toString();
                String serving = rServing.getText().toString();
                String prepTime = rPrepTime.getText().toString();
                String cookingTime = rCookingTime.getText().toString() ;
                String ingredient = rIngredients.getText().toString();
                String instruction = rInstrucitons.getText().toString() ;
                Recipe r = new Recipe();
                r.setRecipeTitle(title);
                r.setCategory(category);
                r.setServing(serving);
                r.setPrepTime(prepTime);
                r.setCookingTime(cookingTime);
                r.setIngredients(ingredient);
                r.setInstructions(instruction);

                //add recipe to db
                recipeViewModel.insertRecipe(r);
                Toast.makeText(getActivity(), "Recipe added successfully", Toast.LENGTH_LONG).show();

                title = ""; category = "" ; serving = "" ; prepTime = "" ;
                cookingTime  = "" ; ingredient = "" ; instruction = "" ;
            }
        });
        return view;
    }

}
