package com.muneiah.example.covid19apionretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Covid19Instance
{
    static Retrofit retrofit;
    public static final String BASE_URL="https://api.covid19api.com/";
    public static Retrofit getInstance(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
