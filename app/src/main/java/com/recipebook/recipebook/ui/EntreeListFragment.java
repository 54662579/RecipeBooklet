package com.recipebook.recipebook.ui;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipebook.recipebook.MainActivity;
import com.recipebook.recipebook.R;
import com.recipebook.recipebook.databinding.ItemRecyclerViewBinding;
import com.recipebook.recipebook.db.Recipe;
import com.recipebook.recipebook.db.RecipeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntreeListFragment extends android.support.v4.app.Fragment {

    //private RecipeViewModel recipeViewModel;
    public static final String TAG = "RecipeListViewModel";

    private RecipeAdapter rAdapter;

    private ItemRecyclerViewBinding binding;

    public EntreeListFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.item_recycler_view, container, false);
        rAdapter = new RecipeAdapter(rRecipeCallBack);
        binding.recyclerView.setAdapter(rAdapter);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel
                .class);
        subscribeUi(recipeViewModel);
    }

    private void subscribeUi(RecipeViewModel viewModel){
        viewModel.getRecipeByCategory("Entree").observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipeList) {
                if (recipeList != null) {
                    rAdapter.setRecipeList(recipeList);
                }
            }
        });
    }


    private final RecipeCallBack rRecipeCallBack = new RecipeCallBack() {
        @Override
        public void onClick(Recipe recipe) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                ((MainActivity)getActivity()).showDetail(recipe);
            }

        }
    };

}
