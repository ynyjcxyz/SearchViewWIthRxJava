package com.example.android.searchcatbyrxjava;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpClientUtil {

    public static OkHttpClient buildOkHttpClient(){
        return new OkHttpClient.Builder().addInterceptor(logInterceptor()).build();
    }

    private static HttpLoggingInterceptor logInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }
}
