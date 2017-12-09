package com.mtw.movie_poc_screen.network.responses;

import com.google.gson.annotations.SerializedName;
import com.mtw.movie_poc_screen.data.vo.MoviePopularVO;

import java.util.List;


/**
 * Created by Aspire-V5 on 12/6/2017.
 */

public class GetMovieResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("pageNo")
    private int pageNo;

    @SerializedName("popular-movies")
    private List<MoviePopularVO> popularMovies;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<MoviePopularVO> getPopularMovies() {
        return popularMovies;
    }
}
