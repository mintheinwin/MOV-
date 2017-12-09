package com.mtw.movie_poc_screen.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.mtw.movie_poc_screen.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public abstract class BaseRecyclerAdapter<T extends BaseViewHolder, W> extends RecyclerView.Adapter<T> {

    protected List<W> mData;
    protected LayoutInflater mLayoutInflater;

    public BaseRecyclerAdapter(Context context) {
        mData = new ArrayList<>();
        mLayoutInflater = mLayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void appendNewData(List<W> movieData) {
        mData.addAll(movieData);
        notifyDataSetChanged();
    }

}
