package com.example.android.myapplication.utils;

import android.content.Context;

import com.example.android.myapplication.R;

public final class ImageUtils {

    private ImageUtils() {

    }

    public static int getImageResourceId(Context context, String recipeName) {
        int drawableId = -1;
        if (recipeName.equals(context.getString(R.string.nutella_pie))) {
            drawableId = R.drawable.nutella_pie;
        } else if (recipeName.equals(context.getString(R.string.brownies))) {
            drawableId = R.drawable.brownies;
        } else if (recipeName.equals(context.getString(R.string.yellow_cake))) {
            drawableId = R.drawable.yellow_cake;
        } else if (recipeName.equals(context.getString(R.string.cheesecake))) {
            drawableId = R.drawable.cheesecake;
        }
        return drawableId;
    }
}