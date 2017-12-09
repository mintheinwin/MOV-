package com.mtw.movie_poc_screen.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.mtw.movie_poc_screen.R;
import com.mtw.movie_poc_screen.adapters.MoviesPagerAdapter;
import com.mtw.movie_poc_screen.data.models.MovieModel;
import com.mtw.movie_poc_screen.data.vo.MoviePopularVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aspire-V5 on 9/6/2017.
 */

public class MovieDetailsOverviewActivity extends BaseActivity {

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.vp_movie_overview_images)
    ViewPager vpMovieOverview;

    @BindView(R.id.tv_released_date)
    TextView tvReleasedDate;

    @BindView(R.id.tv_movie_overview)
    TextView tvMovieOverview;

    private static final String tap_movie_id = "fragment_id";
    private MoviePopularVO mMovie;
    MoviesPagerAdapter moviesPagerAdapter;

    public static Intent newIntent(Context context, MoviePopularVO movies){
        Intent intent = new Intent(context, MovieDetailsOverviewActivity.class);
        intent.putExtra(tap_movie_id, movies.getId());
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);
        ButterKnife.bind(this, this);

        moviesPagerAdapter = new MoviesPagerAdapter(getApplicationContext());
        vpMovieOverview.setAdapter(moviesPagerAdapter);
        vpMovieOverview.setOffscreenPageLimit(moviesPagerAdapter.getCount());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int movieId = bundle.getInt(tap_movie_id);
            mMovie = MovieModel.getInstance().getMovieById(movieId);
            bindData(mMovie);
        }

        Map<String, Object> mapsample=new HashMap<>();
        List<Object> lisample=new ArrayList<>(mapsample.values());

    }

    private void bindData(MoviePopularVO movie){
        if(movie.getBackdropPath() != null){
            List<String> images = new ArrayList<>() ;
            images.add(movie.getBackdropPath());
            moviesPagerAdapter.setImages(images);
        }

        collapsingToolbarLayout.setTitle(movie.getTitle());
        tvReleasedDate.setText(movie.getReleaseDate());
        tvMovieOverview.setText(movie.getOverview());

    }
}
