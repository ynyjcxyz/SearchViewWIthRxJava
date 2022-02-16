package com.example.android.searchcatbyrxjava;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderForCatsInfo extends RecyclerView.ViewHolder{
    public final ImageView img;
    public final TextView date_time;
    public final TextView tags;
    public final RelativeLayout parent_layout_cats_info;

    public ViewHolderForCatsInfo(@NonNull View itemView) {
        super(itemView);
        this.img = itemView.findViewById(R.id.img);
        this.date_time = itemView.findViewById(R.id.date_time);
        this.tags = itemView.findViewById(R.id.tags);
        this.parent_layout_cats_info = itemView.findViewById(R.id.parent_layout_cats_info);
    }
}
