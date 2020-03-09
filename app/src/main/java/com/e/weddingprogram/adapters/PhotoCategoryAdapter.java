package com.e.weddingprogram.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.weddingprogram.R;
import com.e.weddingprogram.models.PhotoCard;
import com.e.weddingprogram.view_holders.PhotoCategoryViewHolder.PhotoCategoryActionListener;
import com.e.weddingprogram.view_holders.PhotoCategoryViewHolder;

import java.util.List;

public class PhotoCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<PhotoCard> photoCardList;
   PhotoCategoryActionListener photoCategoryActionListener;

    public PhotoCategoryAdapter(List<PhotoCard> photoCardList, PhotoCategoryActionListener photoCategoryActionListener) {
        this.photoCardList = photoCardList;
        this.photoCategoryActionListener = photoCategoryActionListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_category,viewGroup,false);
        return new PhotoCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((PhotoCategoryViewHolder)viewHolder).bind(photoCardList.get(i),photoCategoryActionListener);
    }

    @Override
    public int getItemCount() {
        return photoCardList.size();
    }
}
