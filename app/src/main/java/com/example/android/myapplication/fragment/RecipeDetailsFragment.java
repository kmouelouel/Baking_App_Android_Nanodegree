package com.example.android.myapplication.fragment;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.android.myapplication.R;
import com.example.android.myapplication.adapters.IngredientAdapter;
import com.example.android.myapplication.adapters.StepAdapter;
import com.example.android.myapplication.models.Ingredient;
import com.example.android.myapplication.models.Recipe;
import com.example.android.myapplication.models.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsFragment extends  Fragment implements StepAdapter.StepOnClickHandler {
    @BindView(R.id.recycler_view_ingredients)
    RecyclerView mRecyclerViewIngredients;

    @BindView(R.id.recycler_view_steps)
    RecyclerView mRecyclerViewSteps;

    @BindView(R.id.nested_scroll_view_recipe_details)
    ScrollView mNestedScrollViewRecipeDetails;
    // Tag for logging
    private static final String TAG = "RecipeDetailsFragment";

    private RecipeDetailsOnClickListener listener;
    private StepAdapter stepAdapter;

    public RecipeDetailsFragment() {

    }



    public static Fragment newInstance(Bundle bundle) {
        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
        recipeDetailsFragment.setArguments(bundle);
        return recipeDetailsFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        List<Ingredient> ingredients = null;
        List<Step> steps = null;
        if (bundle != null) {
            Recipe recipe = bundle.getParcelable(getString(R.string.recipe));
            if (recipe != null) {
                ingredients = recipe.getIngredients();
                steps = recipe.getSteps();
            }
        }
        IngredientAdapter ingredientAdapter = new IngredientAdapter(getContext(), ingredients);
        mRecyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewIngredients.setAdapter(ingredientAdapter);

      stepAdapter = new StepAdapter(getContext(), steps, this);
      mRecyclerViewSteps.setLayoutManager(new LinearLayoutManager(getContext()));
      mRecyclerViewSteps.setAdapter(stepAdapter);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecipeDetailsOnClickListener) {
            listener = (RecipeDetailsOnClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + getString(R.string.must_implement)
                    + RecipeDetailsOnClickListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       outState.putIntArray(getString(R.string.scroll_position),
                new int[]{mNestedScrollViewRecipeDetails.getScrollX(), mNestedScrollViewRecipeDetails.getScrollY()});
        outState.putInt(getString(R.string.selected_row_index), stepAdapter.getSelectedRowIndex());

    }

    @Override
    public void onClick(int position) {
        listener.onStepSelected(position);
    }

    public StepAdapter getStepAdapter() {
        return stepAdapter;
    }

    public interface RecipeDetailsOnClickListener {
        void onStepSelected(int position);
    }
}
