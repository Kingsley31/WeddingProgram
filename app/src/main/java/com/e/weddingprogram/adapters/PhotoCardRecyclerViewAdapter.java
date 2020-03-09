package com.e.weddingprogram.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import com.e.weddingprogram.R;
import com.e.weddingprogram.models.PhotoCard;
import com.e.weddingprogram.view_holders.PhotoCardViewHolder;

import java.util.List;

public class PhotoCardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    PhotoCardViewHolder.PhotoCardViewsClickListener photoCardViewsClickListener;
    List<PhotoCard> photoCardList;

    public PhotoCardRecyclerViewAdapter(PhotoCardViewHolder.PhotoCardViewsClickListener photoCardViewsClickListener, List<PhotoCard> photoCardList) {
        this.photoCardViewsClickListener = photoCardViewsClickListener;
        this.photoCardList = photoCardList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.marriage_photo_card,viewGroup,false);
        return new PhotoCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PhotoCardViewHolder photoCardViewHolder=(PhotoCardViewHolder)viewHolder;
        photoCardViewHolder.bind(photoCardList.get(i),photoCardViewsClickListener);
    }

    @Override
    public int getItemCount() {
        return photoCardList.size();
    }
}
