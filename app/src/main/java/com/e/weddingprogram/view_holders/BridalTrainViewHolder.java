package com.e.weddingprogram.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import com.e.weddingprogram.R;
import com.e.weddingprogram.models.BridalTrainCard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BridalTrainViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bridalTrainImage)
    ImageView bridalTrainImage;
    @BindView(R.id.bridalTrainName)
    TextView bridalTrainName;
    BridalTrainActionListener bridalTrainActionListener;
    BridalTrainCard bridalTrainCard;

    public BridalTrainViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(BridalTrainCard bridalTrainCard,BridalTrainActionListener bridalTrainActionListener){
        this.bridalTrainCard=bridalTrainCard;
        this.bridalTrainActionListener=bridalTrainActionListener;
        setUpBridalTrainImage(bridalTrainCard.getImageId());
        setUpBridalTrainName(bridalTrainCard.getBridalTrainName());
    }

    void setUpBridalTrainImage(int imageId){
        bridalTrainImage.setImageResource(imageId);
    }

    void setUpBridalTrainName(String name){
        bridalTrainName.setText(name);
    }

    @OnClick(R.id.bridalTrainImage)
    public void onBridalImageClicked(){
        bridalTrainActionListener.onBridalTrainClicked(bridalTrainCard);
    }

    public interface BridalTrainActionListener{
        void onBridalTrainClicked(BridalTrainCard bridalTrainCard);
    }
}
