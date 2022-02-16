package com.example.android.searchcatbyrxjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

public class GsonUtil {
    public static Gson createGson(){
        return new GsonBuilder()
                .registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY)
                .create();
    }
}
