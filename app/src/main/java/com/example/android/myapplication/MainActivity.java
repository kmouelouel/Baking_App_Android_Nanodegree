package com.example.android.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.adapters.RecipeAdapter;
import com.example.android.myapplication.models.Recipe;
import com.example.android.myapplication.utils.FetchRecipesTask;
import com.example.android.myapplication.utils.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements  RecipeAdapter.RecipeOnClickHandler  {


    private final String TAG= MainActivity.class.getName();
    //create an ID to identify the loader responsible for loading our recipe data
    private static final int ID_RECIPE_LOADER = 22;
    @BindView(R.id.recycler_view_recipes)
    RecyclerView mRecyclerViewRecipes;
    @BindView(R.id.loading_indicator)
    ProgressBar mProgressBar;
    @BindView(R.id.tv_error_message)
    TextView mTextViewErrorMessage;
    private RecipeAdapter mRecipeAdapter;
    private int mPosition = RecyclerView.NO_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewRecipes.setLayoutManager(layoutManager);
        mRecyclerViewRecipes.setHasFixedSize(true);
        mRecipeAdapter = new RecipeAdapter(this,this);
        mRecyclerViewRecipes.setAdapter(mRecipeAdapter);
        loadRecipeData();
    }
    // create a showLoading method that show the prograssBar and hide the recyclerView
    private void loadRecipeData() {
        if(NetworkUtils.isNetworkAvailable(this)) {
            FetchRecipesTask mFetchRecipesTask = new FetchRecipesTask(this,mRecipeAdapter);
            mFetchRecipesTask.execute();
   }
    }



    @Override
    public void onClick(Recipe recipe) {
        Toast.makeText(getApplicationContext(), recipe.getRecipeName(), Toast.LENGTH_SHORT)
                .show();


    }



}
