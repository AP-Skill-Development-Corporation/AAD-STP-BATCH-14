package com.example.services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Intent i = new Intent(this,MyService.class);
        startService(i);

    }

    public void stopService(View view) {
        Intent stop = new Intent(this,MyService.class);
        stopService(stop);

    }
}