package com.muneiah.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
EditText et_name,et_roll;
StudentEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        et_name=findViewById(R.id.editTextname_updte);
        et_roll=findViewById(R.id.rollnum_update);
        Intent i=getIntent();
        String data_name=i.getStringExtra("name_key");
        String data_roll=i.getStringExtra("roll_key");
        et_name.setText(data_name);
        et_roll.setText(data_roll);
        et_roll.setKeyListener(null);

    }

    public void updateData(View view) {
        String name=et_name.getText().toString();
        String roll=et_roll.getText().toString();
        entity=new StudentEntity();
        entity.setName(name);
        entity.setRollnum(roll);
        MainActivity.dataBase.studentDAO().update(entity);
        Toast.makeText(this, "updated "+name, Toast.LENGTH_SHORT).show();



    }
}