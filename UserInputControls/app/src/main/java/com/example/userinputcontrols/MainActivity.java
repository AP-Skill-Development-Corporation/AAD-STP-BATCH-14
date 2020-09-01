package com.example.userinputcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   EditText et_name,et_email,et_phone,et_password;
   TextView tv;
   RadioButton r_male,female;
   String gender;
   CheckBox ch_c,ch_android,ch_python;
   Spinner sp_branches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.name);
        et_email=findViewById(R.id.email);
        et_phone=findViewById(R.id.phone);
        et_password=findViewById(R.id.password);
        tv=findViewById(R.id.result);
        r_male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        ch_c=findViewById(R.id.c);
        ch_android=findViewById(R.id.android);
        ch_python=findViewById(R.id.pthyon);
        sp_branches=findViewById(R.id.branches);
    }

    public void display(View view) {
        String name=et_name.getText().toString();
        String email=et_email.getText().toString();
        String phone=et_phone.getText().toString();
        String password=et_password.getText().toString();
        if(r_male.isChecked())
        {
        gender=r_male.getText().toString();
        }
        else if(female.isChecked())
        {
            gender = female.getText().toString();
        }
        StringBuilder builder=new StringBuilder();
        if(ch_c.isChecked()){
            builder.append(ch_c.getText().toString()+",");
        }
        if(ch_android.isChecked()){
            builder.append(ch_android.getText().toString()+",");
        }
        if(ch_python.isChecked()){
            builder.append(ch_python.getText().toString());
        }
        String branch=sp_branches.getSelectedItem().toString();
        tv.setText(name+"\n"+email+"\n"+phone+"\n"+password+"\n"+gender+"\n"+builder.toString()+"\n"+branch);
    }
}