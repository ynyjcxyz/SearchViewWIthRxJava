package com.example.android.searchcatbyrxjava;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TagsDataAdapter extends RecyclerView.Adapter<ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<TagsDataModal> tagsDataModalArrayList;

    // creating a constructor for our variables.
    public TagsDataAdapter(ArrayList<TagsDataModal> tagsDataModalArrayList) {
        this.tagsDataModalArrayList = tagsDataModalArrayList;
    }

    // method for filtering our recyclerview items.
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<TagsDataModal> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        tagsDataModalArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_tags_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        TagsDataModal modal = tagsDataModalArrayList.get(position);
        holder.catTags.setText(modal.getCatsTags());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return tagsDataModalArrayList.size();
    }

}