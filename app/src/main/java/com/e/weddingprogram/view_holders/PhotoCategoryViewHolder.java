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

public class PhotoCategoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.categoryPhoto)
    ImageView categoryPhoto;
    @BindView(R.id.categoryText)
    TextView categoryText;
    PhotoCard photoCard;
    PhotoCategoryActionListener photoCategoryActionListener;

    public PhotoCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(PhotoCard photoCard,PhotoCategoryActionListener photoCategoryActionListener){
        this.photoCard=photoCard;
        this.photoCategoryActionListener=photoCategoryActionListener;
        categoryText.setText(photoCard.getTitle());
        categoryPhoto.setImageResource(photoCard.getImage_id());

    }

    @OnClick(R.id.categoryPhoto)
    public void onViewClicked() {
        photoCategoryActionListener.onCategoryClicked(photoCard.getType());
    }

    public interface PhotoCategoryActionListener{
        public void onCategoryClicked(String type);
    }
}
