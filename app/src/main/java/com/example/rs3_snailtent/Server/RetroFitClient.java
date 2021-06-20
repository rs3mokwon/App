package com.example.rs3_snailtent.Server;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {
    private final static String BASE_URL = "http://220.81.195.81:5000/";
    private static Retrofit retrofit = null;

    private RetroFitClient(){

    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
