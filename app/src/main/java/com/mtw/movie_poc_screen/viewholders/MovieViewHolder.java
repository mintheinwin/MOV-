package com.mtw.movie_poc_screen.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mtw.movie_poc_screen.R;
import com.mtw.movie_poc_screen.data.vo.MoviePopularVO;
import com.mtw.movie_poc_screen.delegates.MovieItemDelegate;
import com.mtw.movie_poc_screen.utils.APIConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public class MovieViewHolder extends BaseViewHolder<MoviePopularVO> {

    @BindView(R.id.iv_poster)
    ImageView ivPoster;

    @BindView(R.id.tv_vote_average)
    TextView tvVoteAverage;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    private MovieItemDelegate mDelegate;
    private MoviePopularVO mMovie;

    public MovieViewHolder(View itemView, MovieItemDelegate movieItemDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = movieItemDelegate;
    }

    @Override
    public void setData(MoviePopularVO movie) {
        mMovie = movie;

        Glide.with(ivPoster.getContext())
                .load(APIConstants.IMAGE_API + movie.getPosterPath())
                .into(ivPoster);

        tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        tvTitle.setText(movie.getTitle());
    }

    @OnClick(R.id.btn_movie_overview)
    public void onTapMovieOverview(View view){
        mDelegate.onTapMovieOverview(mMovie);
    }

    @Override
    public void onClick(View view) {

    }
}
