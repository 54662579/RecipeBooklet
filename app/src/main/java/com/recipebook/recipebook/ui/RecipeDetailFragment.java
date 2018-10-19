package com.recipebook.recipebook.ui;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipebook.recipebook.MainActivity;
import com.recipebook.recipebook.R;
import com.recipebook.recipebook.databinding.FragmentRecipeDetailBinding;
import com.recipebook.recipebook.db.Recipe;
import com.recipebook.recipebook.db.RecipeRepository;
import com.recipebook.recipebook.db.RecipeViewModel;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends android.support.v4.app.Fragment {

    private static final String KEY_RECIPE_ID = "recipe_id";

    private ImageView viewImage;
    private TextView viewTitle;
    private TextView viewCategory;
    private TextView viewServing;
    private TextView viewPrepTime;
    private TextView viewCookTime;
    private TextView viewIngredient;
    private TextView viewInstruction;
    private ImageButton deleteButton;
    private ImageButton editButton;
    private RecipeViewModel recipeViewModel;





    public RecipeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        viewImage = view.findViewById(R.id.viewRecipeImage);
        viewTitle = view.findViewById(R.id.viewRecipeTitle);
        viewCategory = view.findViewById(R.id.viewCategory);
        viewServing = view.findViewById(R.id.viewServing);
        viewPrepTime = view.findViewById(R.id.viewPreptime);
        viewCookTime = view.findViewById(R.id.viewCookTime);
        viewIngredient = view.findViewById(R.id.viewIngredient);
        viewInstruction = view.findViewById(R.id.viewInstruction);
        deleteButton = view.findViewById(R.id.deleteButton);
        editButton = view.findViewById(R.id.editButton);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        Recipe r = recipeViewModel.getRecipeById(getArguments().getInt(KEY_RECIPE_ID));
      //  RecipeRepository repository = new RecipeRepository(getActivity().getApplication());
     //   Recipe r = repository.getRecipeById(getArguments().getInt(KEY_RECIPE_ID));

        try {
            String fileName = r.getImagePath();
           FileInputStream imageInput = getContext().openFileInput(fileName);
           imageInput.close();
           viewImage.setImageBitmap(BitmapFactory.decodeStream(getContext().openFileInput
                    (fileName)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        viewTitle.setText(r.getRecipeTitle());
        viewCategory.setText(r.getCategory());
        viewServing.setText(r.getServing());
        viewPrepTime.setText(r.getPrepTime());
        viewCookTime.setText(r.getCookingTime());
        viewIngredient.setText(r.getIngredients());
        viewInstruction.setText(r.getInstructions());
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteFragment fragment = DeleteFragment.forRecipe(r.getId());
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        fragment).addToBackStack(null).commit();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditRecipeFragment fragment = EditRecipeFragment.forRecipe(r.getId());
               MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        fragment).addToBackStack(null).commit();
            }
        });

    }

    public static RecipeDetailFragment forRecipe(int recipeId){
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

}
