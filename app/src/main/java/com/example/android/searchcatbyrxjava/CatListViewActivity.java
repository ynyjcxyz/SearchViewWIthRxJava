package com.example.android.searchcatbyrxjava;

import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CatListViewActivity extends AppCompatActivity {
    private RecyclerView catsInfoRecyclerView;
    private CatsInfoAdapter catsInfoAdapter;
    private ArrayList<CatsInfo> catsInfoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_list_view);

        setRecyclerView();

        bindData();
    }

    private void setRecyclerView() {
        catsInfoRecyclerView = findViewById(R.id.cats_list);
        new LinearLayoutManager(this).setOrientation(LinearLayoutManager.VERTICAL);
        catsInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        catsInfoRecyclerView.setHasFixedSize(true);

        catsInfoAdapter = new CatsInfoAdapter(this,catsInfoArrayList);
        catsInfoRecyclerView.setAdapter(catsInfoAdapter);
    }

    private void bindData() {
        CatsDataService
                .getCatsDto(getIntent().getStringExtra("tag"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess,this::onError);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onSuccess(List<CatsInfo> catsInfo) {
        catsInfoArrayList = new ArrayList<>();
        catsInfoArrayList.addAll(catsInfo);

        catsInfoAdapter.setInfoList(catsInfoArrayList);
        catsInfoAdapter.notifyDataSetChanged();
    }

    private void onError(Throwable throwable) {
        Log.d("TAG", "Failed! Response = " + throwable);
    }
}
