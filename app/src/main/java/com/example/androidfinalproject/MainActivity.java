package com.example.androidfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.androidfinalproject.CarChargingStation.CarChargingStation;
import com.example.androidfinalproject.currencyConverter.CurrencyConverter;
import com.example.androidfinalproject.news.News;
import com.example.androidfinalproject.recipeFinder.RecipeFinder;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static final String ACTIVITY_NAME = "MAIN_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btn_charge = (ImageButton)findViewById(R.id.btn_charge);
        btn_charge.setOnClickListener(bt -> {
            Intent nextPage = new Intent(MainActivity.this, CarChargingStation.class);
            startActivity(nextPage);
        });

        ImageButton btn_news = (ImageButton)findViewById(R.id.btn_news);
        btn_news.setOnClickListener(bt -> {
            Intent nextPage = new Intent(MainActivity.this, News.class);
            startActivity(nextPage);
        });

        ImageButton btn_recipe = (ImageButton)findViewById(R.id.btn_recipe);
        btn_recipe.setOnClickListener(bt -> {
            Intent nextPage = new Intent(MainActivity.this, RecipeFinder.class);
            startActivity(nextPage);
        });


        ImageButton btn_currency = (ImageButton)findViewById(R.id.btn_currency);
        btn_currency.setOnClickListener(bt -> {
            Intent nextPage = new Intent(MainActivity.this, CurrencyConverter.class);
            startActivity(nextPage);
        });
    }

}
