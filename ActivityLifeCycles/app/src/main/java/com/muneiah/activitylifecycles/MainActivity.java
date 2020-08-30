package com.muneiah.activitylifecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView mTextview;
//First Call back method 1
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextview=findViewById(R.id.tv);
        mTextview.append("onCreate()\n");
        Log.i("MainAcivity","onCreate()");
        Toast.makeText(this, "App is Created ", Toast.LENGTH_SHORT).show();
    }

//2
    @Override
    protected void onStart() {
        super.onStart();
        mTextview.append("onStart()\n");
        Log.i("MainAcivity","onStart()");
        Toast.makeText(this, "App is Started ", Toast.LENGTH_SHORT).show();

    }
//3
    @Override
    protected void onResume() {
        super.onResume();
        mTextview.append("onResume()\n");
        Log.i("MainAcivity","onResume()");
        Toast.makeText(this, "App is Resumed ", Toast.LENGTH_SHORT).show();

    }

    //4
    @Override
    protected void onPause() {
        super.onPause();
        mTextview.append("onPause()\n");
        Log.i("MainAcivity","onPause()");
        Toast.makeText(this, "App is Paused ", Toast.LENGTH_SHORT).show();

    }
//5
    @Override
    protected void onStop() {
        super.onStop();
        mTextview.append("onStop()\n");
        Log.i("MainAcivity","onStop()");
        Toast.makeText(this, "App is Stopped ", Toast.LENGTH_SHORT).show();

    }
    //6

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTextview.append("onDestroy()\n");
        Log.i("MainAcivity","onDestroy()");
        Toast.makeText(this, "App is Destroyed ", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mTextview.append("onReStart()\n");
        Log.i("MainAcivity","onReStart()");
        Toast.makeText(this, "App is Re-Started ", Toast.LENGTH_SHORT).show();

    }
}
