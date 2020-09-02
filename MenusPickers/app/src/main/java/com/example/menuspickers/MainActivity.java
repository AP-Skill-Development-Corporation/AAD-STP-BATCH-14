package com.example.menuspickers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button b_alertdialog,b_datepicker;
    int c_year,c_month,c_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_alertdialog=findViewById(R.id.alert);
        b_alertdialog.setOnClickListener(this);
        b_datepicker=findViewById(R.id.date);
        b_datepicker.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(this, "selected the settings", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(this,Settings.class);
                startActivity(intent);
                break;
            case  R.id.favorite:
                Toast.makeText(this, "selected the favourite", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:
                Toast.makeText(this, "seleected the contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:

                Toast.makeText(this, "selected the search", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
  switch (view.getId()){
    case R.id.alert:
        openALertDialog();
        break;
      case R.id.date:
          openDatePicker();
          break;
} }

    private void openDatePicker() {

        Calendar c= Calendar.getInstance();
        c_year=c.get(Calendar.YEAR);
        c_month=c.get(Calendar.MONTH);
        c_date=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                String mydate=i+"-"+(i1+1)+"-"+i2;
                Toast.makeText(MainActivity.this, mydate, Toast.LENGTH_SHORT).show();
            }
        },c_year,c_month,c_date);
        datePickerDialog.show();
    }

    private void openALertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage("Are you sure to exit");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
           finish();
            }
        });
      builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              dialogInterface.dismiss();
          }
      });
      builder.show();
    }
}