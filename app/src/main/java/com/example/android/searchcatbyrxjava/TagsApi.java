package com.example.android.searchcatbyrxjava;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TagsApi {
    String BASE_URL = "https://cataas.com/";

    @GET("api/{tags}")
    Single<List<String>> getTags(
            @Path("tags") String tags);
}
