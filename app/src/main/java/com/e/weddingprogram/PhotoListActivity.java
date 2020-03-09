package com.e.weddingprogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.e.weddingprogram.adapters.PhotoListViewPagerAdapter;
import com.e.weddingprogram.models.PhotoCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoListActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    public static final String PHOTO_TYPE = "type";
    public static final String PHOTO_CARD = "photocard";
    public static final String PHOTO_IS_SET = "itemisset";
    @BindView(R.id.activityTitle)
    TextView activityTitle;
    @BindView(R.id.photoList)
    ViewPager photoList;
    @BindView(R.id.itemCount)
    TextView itemCount;
    int currentSlide=0;
    List<PhotoCard> photoCardList;
    PhotoCard selectedPhotoCard;
    boolean itemIsSet=false;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_photo_list);
        ButterKnife.bind(this);
        getIntentData();
        loadDataBaseOnType();
        setUpPhotoList(photoCardList,itemIsSet);
    }

    void  hideStatusBar(){
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void loadDataBaseOnType(){

        switch (type){
            case PhotoCard.TYPE_ENGAGEMENT:
                loadEngagementPhotos();
                activityTitle.setText("Proposal Photos");
                break;
            case PhotoCard.TYPE_PREWEDDING:
                loadPreWeddingPhotos();
                activityTitle.setText("Pre-Wedding Photos");
                break;
            case PhotoCard.TYPE_TRADITIONAL:
                loadTraditionalPhotos();
                activityTitle.setText("Traditional Wedding");
                break;
            case PhotoCard.TYPE_BRIDAL:
                loadBridalPhotos();
                activityTitle.setText("Bridal Train");
                break;
        }
    }

    private void loadBridalPhotos() {
        photoCardList = new ArrayList<>();
        photoCardList.add(new PhotoCard("Blessing Chika", PhotoCard.TYPE_BRIDAL, R.drawable.blessing_chika));
        photoCardList.add(new PhotoCard("Chiamaka Korie", PhotoCard.TYPE_BRIDAL, R.drawable.chiamaka_korie));
        photoCardList.add(new PhotoCard("Gift Johnson", PhotoCard.TYPE_BRIDAL, R.drawable.gift_johnson));
        photoCardList.add(new PhotoCard("Ifeoluwa Peace", PhotoCard.TYPE_BRIDAL,R.drawable.ifeoluwa_peace));
        photoCardList.add(new PhotoCard("Okafor Francisca", PhotoCard.TYPE_BRIDAL,R.drawable.okafor_francisca));
        photoCardList.add(new PhotoCard("Peace Eze", PhotoCard.TYPE_BRIDAL,R.drawable.peace_eze));
        photoCardList.add(new PhotoCard("Obiageli Onuchukwu", PhotoCard.TYPE_BRIDAL,R.drawable.obiageli_onuchukwu));
    }

    private void loadTraditionalPhotos() {
        photoCardList = new ArrayList<>();
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr1));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr2));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr3));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr4));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr5));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr6));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr7));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr8));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr9));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr10));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr11));
    }

    private void loadPreWeddingPhotos() {
        photoCardList = new ArrayList<>();
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw1));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw2));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw3));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw4));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw5));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw6));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw7));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw8));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw9));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw10));
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw11));

    }

    private void loadEngagementPhotos() {
        photoCardList = new ArrayList<>();
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg1));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg2));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg3));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg4));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg5));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg6));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg7));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg8));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg9));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg10));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg11));
        photoCardList.add(new PhotoCard("Engagement", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg12));
    }

    public void getIntentData(){
        Intent intent=getIntent();
        type=intent.getStringExtra(PhotoListActivity.PHOTO_TYPE);
        itemIsSet=intent.getBooleanExtra(PhotoListActivity.PHOTO_IS_SET,false);
        if(itemIsSet==true){
            selectedPhotoCard=intent.getParcelableExtra(PhotoListActivity.PHOTO_CARD);
        }
    }

    public void setUpPhotoList(List<PhotoCard> photoCardList,boolean itemIsSet){
        //initialize the pager adapter
        PhotoListViewPagerAdapter photoListViewPagerAdapter=new PhotoListViewPagerAdapter(photoCardList);
        photoList.setAdapter(photoListViewPagerAdapter);
        photoList.addOnPageChangeListener(this);
        if(itemIsSet){
            photoList.setCurrentItem(getItemPosition(selectedPhotoCard));
        }


    }

    public int getItemPosition(PhotoCard photoCard){
        int itemposition=0;
        for (PhotoCard item:photoCardList) {
            if(item.getTitle().equals(photoCard.getTitle())){
                itemposition=photoCardList.indexOf(item);
                break;
            }
        }
        //itemposition=photoCardList.indexOf(photoCard);
        return itemposition;
    }

    @OnClick(R.id.backBtn)
    public void onViewClicked() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @OnClick(R.id.leftBtn)
    public void onLeftBtnClicked() {
        if(currentSlide==0){
            photoList.setCurrentItem(currentSlide+1);
            return;
        }

        photoList.setCurrentItem(currentSlide-1);
    }

    @OnClick(R.id.rightBtn)
    public void onRightBtnClicked() {
        if(currentSlide==( photoCardList.size()-1)){
            photoList.setCurrentItem(currentSlide-1);
            return;
        }
        photoList.setCurrentItem(currentSlide+1);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        currentSlide=i;
        String itemCountText=(currentSlide+1)+"/"+photoCardList.size();
        itemCount.setText(itemCountText);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
