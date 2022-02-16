package com.example.android.searchcatbyrxjava;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TagsDataAdapter extends RecyclerView.Adapter<ViewHolderForTags> {
    private final AppCompatActivity context;
    // creating a variable for array list and context.
    private ArrayList<String> tagsArrayList;

    // creating a constructor for our variables.
    public TagsDataAdapter(AppCompatActivity context) {
        this.context = context;
    }

    // method for filtering our recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(ArrayList<String> filterList) {
        // below line is to add our filtered
        // list in our course array list.
        tagsArrayList = filterList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderForTags onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_tags_item, parent, false);
        return new ViewHolderForTags(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForTags holder, int position) {
        // setting data to our views of recycler view.
        String tagName = tagsArrayList.get(position);
        holder.catTags.setText("Tag: " + tagName);

        holder.parent_layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, CatListView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //Important
            intent.putExtra("tag", tagName);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return tagsArrayList.size();
    }

}