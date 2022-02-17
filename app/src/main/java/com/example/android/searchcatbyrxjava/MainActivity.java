package com.example.android.searchcatbyrxjava;

import static com.example.android.searchcatbyrxjava.TagsRepository.tagsService;
import static com.uber.autodispose.AutoDispose.autoDisposable;
import static com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider.from;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    // creating variables for
    // our ui components.
    private RecyclerView recyclerList;

    // variable for our adapter
    // class and array list
    private TagsDataAdapter adapter;  //成员变量
    private ArrayList<String> tagsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        bindData();
    }

    private void setRecyclerView() {
        recyclerList = findViewById(R.id.recyclerList);
        new LinearLayoutManager(this).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        recyclerList.setHasFixedSize(true);

        adapter = new TagsDataAdapter(this,tagsArrayList);
        recyclerList.setAdapter(adapter);
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
            ArrayList<String> result = SearchUtil.getFilterList(text,tagsArrayList);
            if(result.isEmpty()){
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(result);
        }
    }

    private void bindData() {
        tagsService("tags")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(from(this)))
                .subscribe(this::onSuccess, this::onError);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onSuccess(List<String> data) {
        // below line we are creating a new array list
        tagsArrayList = new ArrayList<>();
        tagsArrayList.addAll(data);
        adapter.filterList(tagsArrayList);
        adapter.notifyDataSetChanged();
    }

    private void onError(Throwable throwable) {
        Log.d("TAG", "Failed! Response = " + throwable);
    }
}