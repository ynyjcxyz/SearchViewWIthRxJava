package com.example.android.searchcatbyrxjava;

import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CatListView extends AppCompatActivity {
    private RecyclerView catsInfoRecyclerView;
    private CatsInfoAdapter catsInfoAdapter;
    private ArrayList<CatsInfo> catsInfoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_list_view);

        catsInfoRecyclerView = findViewById(R.id.cats_list);
        catsInfoRecyclerView.setHasFixedSize(true);
        catsInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bindData();
    }

    private void bindData() {
        CatsDataService
                .getService(getIntent().getStringExtra("tag"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess,this::onError);
    }

    private void onSuccess(List<CatsInfo> catsInfo) {
        catsInfoArrayList = new ArrayList<>();
        catsInfoArrayList.addAll(catsInfo);
        catsInfoAdapter = new CatsInfoAdapter(getApplicationContext(),catsInfoArrayList);
        catsInfoRecyclerView.setAdapter(catsInfoAdapter);
    }

    private void onError(Throwable throwable) {
        Log.d("TAG", "Failed! Response = " + throwable);
    }
}
