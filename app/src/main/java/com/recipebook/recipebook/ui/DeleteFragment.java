package com.recipebook.recipebook.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.recipebook.recipebook.MainActivity;
import com.recipebook.recipebook.R;
import com.recipebook.recipebook.db.Recipe;
import com.recipebook.recipebook.db.RecipeRepository;


public class DeleteFragment extends android.support.v4.app.Fragment {

    private static final String KEY_RECIPE_ID = "recipe_id";
    private TextView recipeTitle;
    private Button yesButton;
    private Button noButton;



    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        recipeTitle = view.findViewById(R.id.recipeTitle);
        yesButton = view.findViewById(R.id.yesButton);
        noButton = view.findViewById(R.id.noButton);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecipeRepository repository = new RecipeRepository(getActivity().getApplication());
        Recipe deleteRecipe = repository.getRecipeById(getArguments().getInt(KEY_RECIPE_ID));

        if (deleteRecipe == null){
            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new
                    HomeFragment()).addToBackStack(null).commit();
            return;
        }

        recipeTitle.setText(deleteRecipe.getRecipeTitle()+"?");
        String recipeCategory = deleteRecipe.getCategory();
        RecipeListFragment newFragment = RecipeListFragment.sendCategory(recipeCategory);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete recipe
                repository.deleteRecipe(deleteRecipe);

                //delete file from the system.
                getContext().deleteFile(deleteRecipe.getImagePath());

                //go back to the list
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        newFragment).addToBackStack(null).commit();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        newFragment).addToBackStack(null).commit();
            }
        });

    }



    public static DeleteFragment forRecipe(int recipeId) {
        DeleteFragment fragment = new DeleteFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_RECIPE_ID, recipeId);
        fragment.setArguments(args);
        return fragment;
    }

}
