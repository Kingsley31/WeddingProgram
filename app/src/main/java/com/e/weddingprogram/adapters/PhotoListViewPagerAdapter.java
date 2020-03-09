package com.e.weddingprogram.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.e.weddingprogram.R;
import com.e.weddingprogram.models.PhotoCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoListViewPagerAdapter extends PagerAdapter {

    List<PhotoCard> photos;
    @BindView(R.id.image)
    ImageView image;

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (LinearLayout) o;
    }

    //set constructor to adapter
    public PhotoListViewPagerAdapter(List<PhotoCard> photos) {
        this.photos = photos;
    }


    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.photolist_card, container, false);
        ButterKnife.bind(this,view);
        image.setImageResource(photos.get(position).getImage_id());
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
