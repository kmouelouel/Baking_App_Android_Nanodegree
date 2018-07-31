package com.example.android.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.myapplication.fragment.RecipeDetailsFragment;
import com.example.android.myapplication.models.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(getString(R.string.recipe))) {
                Recipe recipe = (Recipe) intent.getParcelableExtra(getString(R.string.recipe));
                if (recipe != null) {
                    this.setTitle(recipe.getRecipeName());
                    // Toast.makeText(getApplicationContext(), recipe.getRecipeName(), Toast.LENGTH_SHORT)
                    //                 .show();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    if (fragmentManager.findFragmentByTag(RecipeDetailsFragment.class.getSimpleName()) != null) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(getString(R.string.recipe), recipe);
                   fragmentManager.beginTransaction()
                            .add(
                                    R.id.container_recipe_details,
                                    RecipeDetailsFragment.newInstance(bundle),
                                    RecipeDetailsFragment.class.getSimpleName()
                            )
                            .commit();
                }
            }
        }
    }
}
