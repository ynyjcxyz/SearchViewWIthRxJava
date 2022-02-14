package com.example.android.searchcatbyrxjava;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // creating variables for
    // our ui components.
    private RecyclerView recyclerList;

    // variable for our adapter
    // class and array list
    private TagsDataAdapter adapter;
    private ArrayList<TagsDataModal> tagsDataModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variables.
        recyclerList = findViewById(R.id.recyclerList);

        // calling method to
        // build recycler view.
        buildRecyclerView();
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
        // creating a new array list to filter our data.
        ArrayList<TagsDataModal> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (TagsDataModal item : tagsDataModalArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCatsTags().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {
        // below line we are creating a new array list
        tagsDataModalArrayList = new ArrayList<>();

        // below line is to add data to our array list.
        tagsDataModalArrayList.add(new TagsDataModal("cute"));
        tagsDataModalArrayList.add(new TagsDataModal("lazy"));
        tagsDataModalArrayList.add(new TagsDataModal("sleepy"));
        tagsDataModalArrayList.add(new TagsDataModal("fat"));
        tagsDataModalArrayList.add(new TagsDataModal("angry"));

        // initializing our adapter class.
        adapter = new TagsDataAdapter(tagsDataModalArrayList);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerList.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        recyclerList.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        recyclerList.setAdapter(adapter);
    }
}