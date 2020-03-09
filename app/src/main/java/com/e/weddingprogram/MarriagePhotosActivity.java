package com.e.weddingprogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.e.weddingprogram.adapters.PhotoCategoryAdapter;
import com.e.weddingprogram.models.PhotoCard;
import com.e.weddingprogram.view_holders.PhotoCategoryViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarriagePhotosActivity extends AppCompatActivity implements PhotoCategoryViewHolder.PhotoCategoryActionListener {

    @BindView(R.id.photoList)
    RecyclerView photoList;
    List<PhotoCard> photoCardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marriage_photos);
        ButterKnife.bind(this);
        loadPhotoCardCategoryList();
        setUpPhotolist(photoCardList);
    }

    public void loadPhotoCardCategoryList() {
        photoCardList = new ArrayList<>();
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw11_icon));
        photoCardList.add(new PhotoCard("Proposal", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg7_icon));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr11_icon));
        //photoCardList.add(new PhotoCard("Bridal Train", PhotoCard.TYPE_BRIDAL, R.drawable.gift_johnson_icon));

    }

    public void setUpPhotolist(List<PhotoCard> photoCardlist){
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        photoList.setLayoutManager(staggeredGridLayoutManager);
        PhotoCategoryAdapter photoCategoryAdapter=new PhotoCategoryAdapter(photoCardlist,this);
        photoList.setAdapter(photoCategoryAdapter);
    }

    @OnClick(R.id.backBtn)
    public void onViewClicked() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }

    @Override
    public void onCategoryClicked(String type) {
        gotoPhotoListActivity(type);
    }


    public void gotoPhotoListActivity(String photocardType) {
        Intent intent = new Intent(this, PhotoListActivity.class);
        intent.putExtra(PhotoListActivity.PHOTO_TYPE,photocardType);
        intent.putExtra(PhotoListActivity.PHOTO_IS_SET,false);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
}
