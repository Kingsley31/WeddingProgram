package com.e.weddingprogram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.e.weddingprogram.adapters.BridalTrainRecyclerViewAdapter;
import com.e.weddingprogram.adapters.PhotoCardRecyclerViewAdapter;
import com.e.weddingprogram.models.BridalTrainCard;
import com.e.weddingprogram.models.PhotoCard;
import com.e.weddingprogram.view_holders.BridalTrainViewHolder;
import com.e.weddingprogram.view_holders.PhotoCardViewHolder.PhotoCardViewsClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity implements BridalTrainViewHolder.BridalTrainActionListener {

    @BindView(R.id.currentActivityTxt)
    WebView currentActivityTxt;
    @BindView(R.id.toggleIcon)
    ImageView toggleIcon;
    @BindView(R.id.drawerView)
    LinearLayout drawerView;
    @BindView(R.id.drawerLayer)
    DrawerLayout drawerLayout;

    List<PhotoCard> photoCardList;
    @BindView(R.id.marriagePhotoList)
    RecyclerView marriagePhotoList;

    List<BridalTrainCard> bridalTrainCardList;
    @BindView(R.id.bridalTrainList)
    RecyclerView bridalTrainList;
    MyDrawerToggler myDrawerToggler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        myDrawerToggler = new MyDrawerToggler(drawerLayout, drawerView, toggleIcon);
        myDrawerToggler.init();

        //Animate current item in the program
        String currentGoingOn = getResources().getString(R.string.chisom_weds_christain);
        showCurrentActivity(currentGoingOn);
        loadPhotoCardGroupList();
        setUpMarriagePhotoList(photoCardList);
        loadBridalTrainData();
        setUpBridalTrainList(bridalTrainCardList);
    }

    private void showCurrentActivity(String currentGoingOn) {
        String summary = "<html style='background-color: #413F3B;'><FONT color='#ffffff' FACE='courier'><marquee behavior='scroll' scrolldelay='220' direction='left' scrollamount=10>"
                + currentGoingOn + "</marquee></FONT></html>";
        currentActivityTxt.loadData(summary, "text/html", "utf-8");
    }

    public void loadPhotoCardGroupList() {
        photoCardList = new ArrayList<>();
        photoCardList.add(new PhotoCard("Pre-Wedding", PhotoCard.TYPE_PREWEDDING, R.drawable.prw10_icon));
        photoCardList.add(new PhotoCard("Proposal", PhotoCard.TYPE_ENGAGEMENT, R.drawable.eg7_icon));
        photoCardList.add(new PhotoCard("Traditional", PhotoCard.TYPE_TRADITIONAL, R.drawable.tr11_icon));

    }

    public void setUpMarriagePhotoList(List<PhotoCard> photoList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        marriagePhotoList.setLayoutManager(linearLayoutManager);
        PhotoCardRecyclerViewAdapter photoCardRecyclerViewAdapter = new PhotoCardRecyclerViewAdapter(photoCardViewsClickListener, photoCardList);
        marriagePhotoList.setAdapter(photoCardRecyclerViewAdapter);
    }

    public void setUpBridalTrainList(List<BridalTrainCard> bridalTrainCardList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bridalTrainList.setLayoutManager(linearLayoutManager);
        BridalTrainRecyclerViewAdapter bridalTrainRecyclerViewAdapter = new BridalTrainRecyclerViewAdapter(bridalTrainCardList, this);
        bridalTrainList.setAdapter(bridalTrainRecyclerViewAdapter);
    }

    PhotoCardViewsClickListener photoCardViewsClickListener = new PhotoCardViewsClickListener() {
        @Override
        public void onViewAllBtnClicked(String photocardType) {
            gotoPhotoListActivity(photocardType);
        }

        @Override
        public void onCardClicked(String photocardType) {
            gotoPhotoListActivity(photocardType);
        }
    };


    public void gotoPhotoListActivity(String photocardType) {
        Intent intent = new Intent(this, PhotoListActivity.class);
        intent.putExtra(PhotoListActivity.PHOTO_TYPE, photocardType);
        intent.putExtra(PhotoListActivity.PHOTO_IS_SET, false);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void gotoPhotoListActivity(String photocardType, PhotoCard photoCard) {
        Intent intent = new Intent(this, PhotoListActivity.class);
        intent.putExtra(PhotoListActivity.PHOTO_TYPE, photocardType);
        intent.putExtra(PhotoListActivity.PHOTO_IS_SET, true);
        intent.putExtra(PhotoListActivity.PHOTO_CARD, photoCard);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void gotoWeddingLocationActivity(String place) {
        Intent intent = new Intent(this, WeddingLocationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }




    @OnClick(R.id.seeAllBridalTrain)
    public void onSeeAllBridalTrainClicked() {
        gotoPhotoListActivity(PhotoCard.TYPE_BRIDAL);
    }

    @OnClick(R.id.weddingProgram)
    public void onWeddingProgramClicked() {
        myDrawerToggler.closeDrawer();
        gotoWeddingProgramActivity();
    }

    private void gotoWeddingProgramActivity() {
        Intent intent = new Intent(this, WeddingProgramActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @OnClick(R.id.location)
    public void onLocationClicked() {
        myDrawerToggler.closeDrawer();
        gotoWeddingLocationActivity("all");
    }

    @OnClick(R.id.orderOfPhotograph)
    public void onOrderOfPhotographClicked() {
        myDrawerToggler.closeDrawer();
        gotoOrderOfPhotograhpActivity();
    }

    private void gotoOrderOfPhotograhpActivity() {
        Intent intent = new Intent(this, OrderOfPhotograpghActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @OnClick(R.id.appreciation)
    public void onAppreciationClicked() {
        myDrawerToggler.closeDrawer();
        gotoAppreciationActivity();
    }

    private void gotoAppreciationActivity() {
        Intent intent = new Intent(this, AppreciationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @OnClick(R.id.castAndCrew)
    public void onCastAndCrewClicked() {
        myDrawerToggler.closeDrawer();
        gotoCastAndCrewActivity();
    }

    private void gotoCastAndCrewActivity() {
        Intent intent = new Intent(this, CastAndCrewActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @OnClick(R.id.seeMoreMarriagePhotos)
    public void onSeeMoreMarriagePhotosClicked() {
        gotoMarriagePhotosActivity();
    }

    @OnClick(R.id.photos)
    public void onPhotosClicked() {
        myDrawerToggler.closeDrawer();
        gotoMarriagePhotosActivity();
    }

    private void gotoMarriagePhotosActivity() {
        Intent intent = new Intent(this, MarriagePhotosActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void onBridalTrainClicked(BridalTrainCard bridalTrainCard) {
        PhotoCard photoCard = new PhotoCard(bridalTrainCard.getBridalTrainName(), PhotoCard.TYPE_BRIDAL, getBridalImageId(bridalTrainCard.getBridalTrainName()));
        gotoPhotoListActivity(PhotoCard.TYPE_BRIDAL, photoCard);
    }

    public void loadBridalTrainData() {
        bridalTrainCardList = new ArrayList<>();
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.blessing_chika_icon, "Blessing Chika"));
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.chiamaka_korie_icon, "Chiamaka Korie"));
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.gift_johnson_icon, "Gift Johnson"));
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.ifeoluwa_peace_icon, "Ifeoluwa Peace"));
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.okafor_francisca_icon, "Okafor Francisca"));
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.peace_eze_icon, "Peace Eze"));
        bridalTrainCardList.add(new BridalTrainCard(R.drawable.obiageli_onuchukwu_icon, "Obiageli Onuchukwu"));

        Collections.shuffle(bridalTrainCardList);
    }

    public int getBridalImageId(String name) {
        int id = 0;
        switch (name) {
            case "Blessing Chika":
                return R.drawable.blessing_chika;
            case "Chiamaka Korie":
                return R.drawable.chiamaka_korie;
            case "Gift Johnson":
                return R.drawable.gift_johnson;
            case "Ifeoluwa Peace":
                return R.drawable.ifeoluwa_peace;
            case "Okafor Francisca":
                return R.drawable.okafor_francisca;
            case "Peace Eze":
                return R.drawable.peace_eze;
            case "Obiageli Onuchukwu":
                return R.drawable.obiageli_onuchukwu;
        }

        return id;

    }

    @OnClick(R.id.bridalTrainMenu)
    public void onBridalMenuClicked() {
        myDrawerToggler.closeDrawer();
        gotoPhotoListActivity(PhotoCard.TYPE_BRIDAL);
    }

    @OnClick(R.id.developer)
    public void onDeveloperClicked() {
        myDrawerToggler.closeDrawer();
        gotoDeveloperActivity();
    }

    private void gotoDeveloperActivity() {
        Intent intent = new Intent(this, DevelopersActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }
}
