package com.example.movieapp.Serviceapi;

import com.example.movieapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    //The service interface define the structure
    // and behaviour of api request
    // act as a bridge between your app and api
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apikey);
}
