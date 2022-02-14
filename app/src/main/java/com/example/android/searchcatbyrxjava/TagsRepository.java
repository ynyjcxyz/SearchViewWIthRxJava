package com.example.android.searchcatbyrxjava;

import java.util.List;
import io.reactivex.Single;

public class TagsRepository {
    public static Single<List<String>> tagsService(String tags) {
        return TagsService.tagsService().getTags(tags);
    }
}
