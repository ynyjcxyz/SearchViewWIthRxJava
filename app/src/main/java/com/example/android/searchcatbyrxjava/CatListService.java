package com.example.android.searchcatbyrxjava;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatListService {
    String CATS_LIST_BASE_URL = "https://cataas.com/api/";
    @GET("cats")
    Single<List<CatsInfo>> listRepos (@Query("tags") String parameterQuery);
}