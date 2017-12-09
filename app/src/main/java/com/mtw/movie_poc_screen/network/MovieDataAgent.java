package com.mtw.movie_poc_screen.network;

/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public interface MovieDataAgent {

    void loadPopularMovies(String accessToken, int pageNo);
}
