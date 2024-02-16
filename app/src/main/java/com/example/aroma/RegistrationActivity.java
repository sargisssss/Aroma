package com.example.aroma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void back(View view) {
        Intent intent = new Intent(RegistrationActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        Intent intent = new Intent(RegistrationActivity.this, SignInContinueActivity.class);
        startActivity(intent);
    }
    public void next(View view) {
        Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}