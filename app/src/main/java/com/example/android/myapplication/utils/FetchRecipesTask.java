package com.example.android.myapplication.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.myapplication.MainActivity;
import com.example.android.myapplication.R;
import com.example.android.myapplication.adapters.RecipeAdapter;
import com.example.android.myapplication.models.Recipe;

import java.net.URL;
import java.util.List;


public class FetchRecipesTask  extends AsyncTask<Void, Void, List<Recipe>> {

    Context mContext;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipesAdapter;
    public FetchRecipesTask(Context context,RecipeAdapter recipesAdapter){
        mContext = context;
        mRecipesAdapter = recipesAdapter;
        mLoadingIndicator = mLoadingIndicator = (ProgressBar) ((MainActivity) mContext).findViewById(R.id.loading_indicator);
        mErrorMessageDisplay = (TextView) ((MainActivity) mContext).findViewById(R.id.tv_error_message);
        mRecyclerView = (RecyclerView)((MainActivity) mContext).findViewById(R.id.recycler_view_recipes);
    }
    @Override
    protected List<Recipe> doInBackground(Void... voids) {
        URL recipesRequestUrl  = NetworkUtils.buildUrl();
        try {
            String jsonRecipeResponse  = NetworkUtils.getResponseFromHttpUrl(recipesRequestUrl);
            List<Recipe> jsonRecipeData  = OpenRecipesJsonUtils.getSimpleWeatherStringsFromJson(mContext, jsonRecipeResponse);
            return jsonRecipeData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(List<Recipe> recipes) {
        super.onPostExecute(recipes);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (recipes != null) {
            showRecipeDataView();
            mRecipesAdapter.setRecipes(recipes);

        } else {
            showErrorMessage();
        }
    }

    private void showRecipeDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);

    }

    private void showErrorMessage() {
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);

    }
}
