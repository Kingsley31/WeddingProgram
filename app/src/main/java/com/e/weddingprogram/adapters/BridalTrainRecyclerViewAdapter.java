package com.e.weddingprogram.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.e.weddingprogram.R;
import com.e.weddingprogram.models.BridalTrainCard;
import com.e.weddingprogram.view_holders.BridalTrainViewHolder;

import java.util.List;

public class BridalTrainRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<BridalTrainCard> bridalTrainCardList;
    BridalTrainViewHolder.BridalTrainActionListener bridalTrainActionListener;

    public BridalTrainRecyclerViewAdapter(List<BridalTrainCard> bridalTrainCardList, BridalTrainViewHolder.BridalTrainActionListener bridalTrainActionListener) {
        this.bridalTrainCardList = bridalTrainCardList;
        this.bridalTrainActionListener=bridalTrainActionListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bridal_train_card,viewGroup,false);
        return new BridalTrainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((BridalTrainViewHolder)viewHolder).bind(bridalTrainCardList.get(i),bridalTrainActionListener);
    }

    @Override
    public int getItemCount() {
        return bridalTrainCardList.size();
    }
}
