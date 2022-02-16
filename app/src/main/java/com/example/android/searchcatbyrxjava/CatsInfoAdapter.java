package com.example.android.searchcatbyrxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class CatsInfoAdapter extends RecyclerView.Adapter<ViewHolderForCatsInfo> {
    private ArrayList<CatsInfo> catsInfoArrayList;
    private Context context;

    public CatsInfoAdapter(Context context, ArrayList<CatsInfo> catsInfoArrayList) {
        this.context = context;
        this.catsInfoArrayList = catsInfoArrayList;
        CatsInfoAdapter catsInfoAdapter = this;

    }

    @NonNull
    @Override
    public ViewHolderForCatsInfo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cats_detail, parent, false);
        return new ViewHolderForCatsInfo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForCatsInfo holder, int position) {
        String imgUrl = "https://cataas.com/cat/" + catsInfoArrayList.get(position).id();
        Glide
                .with(context)
                .load(imgUrl)
                .error(R.drawable.no_data)
                .into(holder.img);

        holder.date_time.setText(catsInfoArrayList.get(position).dateAndTime());

        List<String> tagsList = catsInfoArrayList.get(position).CatTags();
        StringBuilder tag = new StringBuilder(" ");
        for(int i = 0; i < (tagsList != null ? tagsList.size() : 0); i++){
            tag.append(tagsList.get(i)).append(",");
        }
        holder.tags.setText("Tags:" + tag);
    }

    @Override
    public int getItemCount() {
        return catsInfoArrayList.size();
    }
}
