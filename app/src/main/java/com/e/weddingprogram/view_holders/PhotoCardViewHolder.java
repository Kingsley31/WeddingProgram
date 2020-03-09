package com.e.weddingprogram.view_holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;




import com.e.weddingprogram.R;
import com.e.weddingprogram.models.PhotoCard;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoCardViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.groupFrontImage)
    ImageView groupFrontImage;
    @BindView(R.id.imageGroupName)
    TextView imageGroupName;
    PhotoCard photoCard;
    PhotoCardViewsClickListener photoCardViewsClickListener;


    public PhotoCardViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(PhotoCard photoCard,PhotoCardViewsClickListener photoCardViewsClickListener){
        this.photoCard=photoCard;
        this.photoCardViewsClickListener=photoCardViewsClickListener;
        setGroupFrontImage(photoCard.getImage_id());
        setImageGroupName(photoCard.getTitle());

    }

    void setGroupFrontImage(int resourceId){
        groupFrontImage.setImageResource(resourceId);
    }

    void setImageGroupName(String groupName){
        imageGroupName.setText(groupName);
    }


    @OnClick(R.id.viewAllBtn)
    public void onViewAllBtnClicked() {
        photoCardViewsClickListener.onViewAllBtnClicked(photoCard.getType());
    }

    @OnClick(R.id.imageGroup)
    public void onImageGroupClicked() {
        photoCardViewsClickListener.onCardClicked(photoCard.getType());
    }

    public interface PhotoCardViewsClickListener{
        void onViewAllBtnClicked(String photocardType);
        void onCardClicked(String photocardType);
    }
}
