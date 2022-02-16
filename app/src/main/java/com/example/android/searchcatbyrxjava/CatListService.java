package com.example.android.searchcatbyrxjava;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatListService {
    // https://cataas.com/api/cats?tags=sleeping
    String CATS_LIST_BASE_URL = "https://cataas.com/api/";
    @GET("cats")
    Single<List<CatsInfo>> listRepos (@Query("tags") String parameterQuery);
}