package com.mtw.movie_poc_screen.data.models;

import com.mtw.movie_poc_screen.data.vo.MoviePopularVO;
import com.mtw.movie_poc_screen.events.RestApiEvents;
import com.mtw.movie_poc_screen.network.MovieDataAgentImpl;
import com.mtw.movie_poc_screen.utils.APIConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private List<MoviePopularVO> mMovies;
    private int mMoviesPageIndex = 1;

    private MovieModel() {
        EventBus.getDefault().register(this);
        mMovies = new ArrayList<>();
    }

    public static MovieModel getInstance() {
        if(objInstance == null){
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public void startLoadingPopularMovies(){
        MovieDataAgentImpl.getInstance().loadPopularMovies(APIConstants.ACCESS_TOKEN, mMoviesPageIndex);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMoviesDataLoaded(RestApiEvents.PopularMoviesDataLoadedEvent event){
        mMovies.addAll(event.getLoadedMovies());
        mMoviesPageIndex = event.getLoadedPageIndex() + 1;
    }

    public MoviePopularVO getMovieById(int id){
        for(MoviePopularVO movies : mMovies){
            if(movies.getId()== id){
                return movies;
            }
        }
        return null;
    }
}
