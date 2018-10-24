package com.example.rachel.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rachel.tipcalculator.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        Intent intent = new Intent(getApplicationContext (), MainActivity.class);
        startActivity(intent);
        finish();
    }
}

