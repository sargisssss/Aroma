package com.example.aroma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignInContinueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_continue);
    }

    public void back(View view) {
        Intent intent = new Intent(SignInContinueActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(SignInContinueActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
    public void signIN(View view) {
        Intent intent = new Intent(SignInContinueActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}