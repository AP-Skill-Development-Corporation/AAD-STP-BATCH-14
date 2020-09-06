package com.muneiah.example.covid19apionretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.muneiah.example.covid19apionretrofit.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
       EndpointInterface anInterface=Covid19Instance.getInstance().create(EndpointInterface.class);
        Call<String> c=anInterface.getData();
        c.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
               // Toast.makeText(MainActivity.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray rootArray=new JSONArray(response.body());
                    for (int i=0;i<rootArray.length();i++)
                    {
                        JSONObject rootObj = rootArray.getJSONObject(i);
                        String countryResult = rootObj.getString("Country");
                        String confiredResult = rootObj.getString("Confirmed");
                        String resultDeaths = rootObj.getString("Deaths");
                        String resultRecovered = rootObj.getString("Recovered");
                        String resultActive = rootObj.getString("Active");
                        String resultDate = rootObj.getString("Date");
                        binding.activeTv.setText("Active " + resultActive);
                        binding.confiredTv.setText("Confirmed " + confiredResult);
                        binding.countryTv.setText("Country " + countryResult);
                        binding.deathsTv.setText("Deaths " + resultDeaths);
                        binding.recoveredTv.setText("Confirmed " + resultRecovered);
                        binding.dateTv.setText("Date " + properDateFormat(resultDate));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong with fetching data..", Toast.LENGTH_SHORT).show();
            }
        });
/*how many of you get the output*/

    }

    private String properDateFormat(String resultDate) {
        String inputFormat="yy-mm-dd";
        String outputformat="dd-mm-yy";
        SimpleDateFormat inputForm=new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputForm=new SimpleDateFormat(outputformat);
       String s=null;
        Date d=null;
        try {
            d=inputForm.parse(resultDate);
            s=outputForm.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return s;
    }

}