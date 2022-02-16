package com.example.android.searchcatbyrxjava;

import static com.example.android.searchcatbyrxjava.GsonUtil.createGson;
import static com.example.android.searchcatbyrxjava.OkhttpClientUtil.buildOkHttpClient;

import java.util.List;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatsDataService {
    static Single<List<CatsInfo>> getService(String parameterQuery) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CatListService.CATS_LIST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build();
        return retrofit
                .create(CatListService.class)
                .listRepos(parameterQuery);
    }
}
