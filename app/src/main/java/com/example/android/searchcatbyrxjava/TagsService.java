package com.example.android.searchcatbyrxjava;

import static com.example.android.searchcatbyrxjava.GsonUtil.createGson;
import static com.example.android.searchcatbyrxjava.OkhttpClientUtil.buildOkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TagsService {
    static TagsApi tagsService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TagsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //necessary
                .client(buildOkHttpClient())
                .build();
        return retrofit.create(TagsApi.class);
    }
}
