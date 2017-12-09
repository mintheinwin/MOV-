package com.mtw.movie_poc_screen.network;

import com.google.gson.Gson;
import com.mtw.movie_poc_screen.events.RestApiEvents;
import com.mtw.movie_poc_screen.network.responses.GetMovieResponse;
import com.mtw.movie_poc_screen.utils.APIConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public class MovieDataAgentImpl implements MovieDataAgent {

    private static MovieDataAgent objInstance;

    private MovieAPI movieAPI;

    private MovieDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
    }

    public static MovieDataAgent getInstance() {
        if(objInstance == null) {
            objInstance = new MovieDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadPopularMovies(String accessToken, int pageNo) {
        Call<GetMovieResponse> loadPopularMoviesCall = movieAPI.loadPopularMovies(accessToken, pageNo);
        loadPopularMoviesCall.enqueue(new Callback<GetMovieResponse>() {
            @Override
            public void onResponse(Call<GetMovieResponse> call, Response<GetMovieResponse> response) {
                GetMovieResponse getMovieResponse = response.body();
                if(getMovieResponse != null && getMovieResponse.getPopularMovies().size() > 0) {
                    RestApiEvents.PopularMoviesDataLoadedEvent popularMoviesDataLoadedEvent = new RestApiEvents.PopularMoviesDataLoadedEvent(getMovieResponse.getPageNo(), getMovieResponse.getPopularMovies());
                    EventBus.getDefault().post(popularMoviesDataLoadedEvent);
                } else {
                    RestApiEvents.EmptyResponseEvent emptyResponseEvent = new RestApiEvents.EmptyResponseEvent("No popular movies could be loaded for now. Please try again later.");
                    EventBus.getDefault().post(emptyResponseEvent);
                }
            }

            @Override
            public void onFailure(Call<GetMovieResponse> call, Throwable t) {
                RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorInvokingAPIEvent);
            }
        });
    }
}
