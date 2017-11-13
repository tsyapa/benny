package com.ddragons.benny.web;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vitaliy Tsyapa on 10/25/2017.
 */

public class RestClient {

    private static API REST_CLIENT;
    private static Retrofit retrofit;
    private static final String ROOT = "http://contxapidev.azurewebsites.net/";

    public static API getApi() {
        return REST_CLIENT;
    }

    static {
        setupRestClient();
    }

    private static void setupRestClient() {
        if (REST_CLIENT == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(loggingInterceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            REST_CLIENT = retrofit.create(API.class);
        }
    }
}
