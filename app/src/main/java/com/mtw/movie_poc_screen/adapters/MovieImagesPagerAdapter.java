package com.mtw.movie_poc_screen.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mtw.movie_poc_screen.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Aspire-V5 on 12/8/2017.
 */

public class MovieImagesPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<String> images;

    public MovieImagesPagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        images = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return images.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_movie_overview_image, container, false);
        ImageView imageView = itemView.findViewById(R.id.iv_details_image);
        if(images != null && images.size() > 0){
            Glide.with(imageView.getContext())
                    .load(images.get(0))
                    .into(imageView);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    public void setImages(List<String> newsImages){
        this.images = newsImages;
        notifyDataSetChanged();
    }
}
