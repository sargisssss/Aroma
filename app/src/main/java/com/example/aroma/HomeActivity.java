package com.example.aroma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void account(View view) {
        Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void likedItems(View view) {
        Intent intent = new Intent(HomeActivity.this, LikedItemsActivity.class);
        startActivity(intent);
    }

    public void catalog(View view) {
        Intent intent = new Intent(HomeActivity.this, CatalogActivity.class);
        startActivity(intent);
    }

    public void cart(View view) {
        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
        startActivity(intent);
    }

    public void blog1(View view) {
        Intent intent = new Intent(HomeActivity.this, BlogActivity1.class);
        startActivity(intent);
    }

    public void blog2(View view) {
        Intent intent = new Intent(HomeActivity.this, BlogActivity2.class);
        startActivity(intent);
    }

    public void blog3(View view) {
        Intent intent = new Intent(HomeActivity.this, BlogActivity3.class);
        startActivity(intent);
    }

    public void blog4(View view) {
        Intent intent = new Intent(HomeActivity.this, BlogActivity4.class);
        startActivity(intent);
    }

    public void blog5(View view) {
        Intent intent = new Intent(HomeActivity.this, BlogActivity5.class);
        startActivity(intent);
    }

}