package com.recipebook.recipebook.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.recipebook.recipebook.R;
import com.recipebook.recipebook.databinding.FragmentRecipeListBinding;
import com.recipebook.recipebook.db.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>{

    private List<Recipe> rRecipeList;


    @Nullable
    private final RecipeCallBack rRecipeCallBack;


    public RecipeAdapter(@Nullable RecipeCallBack rRecipeCallBack){
        this.rRecipeCallBack = rRecipeCallBack;
    }


    void setRecipeList(final List<Recipe> recipeList){
        if (rRecipeList == null){
            rRecipeList = recipeList;
            notifyItemRangeInserted(0, recipeList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return rRecipeList.size();
                }

                @Override
                public int getNewListSize() {
                    return recipeList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return rRecipeList.get(oldItemPosition).getId() ==
                            recipeList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Recipe oldRecipe = rRecipeList.get(oldItemPosition);
                    Recipe newRecipe = recipeList.get(newItemPosition);
                    return newRecipe.getId() == oldRecipe.getId()
                            && newRecipe.getRecipeTitle() == oldRecipe.getRecipeTitle()
                            && newRecipe.getCategory() == oldRecipe.getCategory()
                            && newRecipe.getServing() == oldRecipe.getServing()
                            && newRecipe.getPrepTime() == oldRecipe.getPrepTime()
                            && newRecipe.getCookingTime() == oldRecipe.getCookingTime()
                            && newRecipe.getIngredients() == oldRecipe.getIngredients()
                            && newRecipe.getInstructions() == oldRecipe.getInstructions();
                }
            });
            rRecipeList = recipeList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //this Binding class is autogenerated after adding binding to xml file.

        FragmentRecipeListBinding binding = DataBindingUtil
             .inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_recipe_list,
                     parent, false);;
        binding.setCallback(rRecipeCallBack);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
            Recipe current = rRecipeList.get(i);
            recipeViewHolder.rBinding.setRecipe(current);
            recipeViewHolder.rBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (rRecipeList != null){
            return rRecipeList.size();
        } else return 0;
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder{


      final FragmentRecipeListBinding rBinding;

        public RecipeViewHolder(FragmentRecipeListBinding rBinding) {
            super(rBinding.getRoot());
            this.rBinding = rBinding;
        }
    }


}
