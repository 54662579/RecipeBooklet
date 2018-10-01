package com.recipebook.recipebook.ui;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ContentResolver;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.recipebook.recipebook.R;
import com.recipebook.recipebook.databinding.FragmentRecipeDetailBinding;
import com.recipebook.recipebook.db.Recipe;
import com.recipebook.recipebook.db.RecipeViewModel;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends android.support.v4.app.Fragment {

    private static final String KEY_RECIPE_ID = "recipe_id";
    //private FragmentRecipeDetailBinding binding;

    private ImageView viewImage;
    private TextView viewTitle;





    public RecipeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /*binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_detail, container,
                false);*/

        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        viewImage = view.findViewById(R.id.viewRecipeImage);
        viewTitle = view.findViewById(R.id.viewRecipeTitle);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        Recipe r = recipeViewModel.getRecipeById(getArguments().getInt(KEY_RECIPE_ID));

       /* String fileName = r.getImagePath();
        try {
            //FileInputStream imageOutput = getContext().openFileInput(fileName);
            viewImage.setImageBitmap(BitmapFactory.decodeStream(getContext().openFileInput(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  */
        viewTitle.setText(r.getRecipeTitle());

    }

    public static RecipeDetailFragment forRecipe(int recipeId){
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;

    }

}
