package com.example.android.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.android.myapplication.models.Ingredient;

import java.util.List;

public class IngredientAdapter  extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>{

    private Context mContext;
    private List<Ingredient> mIngredients;
    public IngredientAdapter(Context context, List<Ingredient> ingredients){
        mContext = context;
        mIngredients =ingredients;

    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class IngredientViewHolder{

    }
}
