package com.example.androidfinalproject.news;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class News extends AppCompatActivity {
    public static final String ACTIVITY_NAME = "NEWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In Function onResume()");
    }

    @Override
    protected void onDestroy() {
        Log.e(ACTIVITY_NAME, "In Function onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.e(ACTIVITY_NAME, "In Function onStart()");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.e(ACTIVITY_NAME, "In Function onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(ACTIVITY_NAME, "In Function onStop()");
        super.onStop();
    }
}

