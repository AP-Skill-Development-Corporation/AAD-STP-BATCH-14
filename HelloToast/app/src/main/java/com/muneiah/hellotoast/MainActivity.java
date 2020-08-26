package com.muneiah.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView mTextview;
int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextview=findViewById(R.id.tv);

    }

    public void showToast(View view) {
        Toast.makeText(this, "Welcome to app development", Toast.LENGTH_SHORT).show();
    }

    public void showCount(View view) {
        count++;
        mTextview.setText(String.valueOf(count));

    }
}
