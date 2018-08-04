package com.example.android.myapplication.data;

import android.net.Uri;

public class RecipeContract {
    private static final String AUTHORITY = "com.example.android.myapplication";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
}
