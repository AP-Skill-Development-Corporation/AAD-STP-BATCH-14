package com.muneiah.hellotoast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
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
        if (savedInstanceState!=null && savedInstanceState.containsKey("ap")){
            count=savedInstanceState.getInt("ap");
            mTextview.setText(String.valueOf(count));
        }

    }

    public void showToast(View view) {
        Toast.makeText(this, "Welcome to app development", Toast.LENGTH_SHORT).show();
    }

    public void showCount(View view) {
        count++;
        mTextview.setText(String.valueOf(count));

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ap",count);
    }
}
