package com.example.android.searchcatbyrxjava;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ViewHolder extends RecyclerView.ViewHolder {

    // creating variables for our views.
    public final TextView catTags;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        // initializing our views with their ids.
        catTags = itemView.findViewById(R.id.cat_tags);
    }
}