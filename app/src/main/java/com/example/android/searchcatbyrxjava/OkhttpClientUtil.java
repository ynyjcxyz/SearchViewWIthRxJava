package com.example.android.searchcatbyrxjava;

import androidx.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpClientUtil {
    public static OkHttpClient buildOkHttpClient(){
        return new OkHttpClient
                .Builder()
                .addInterceptor(getInterceptor())
                .build();
    }

    @NonNull
    private static HttpLoggingInterceptor getInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }
}
