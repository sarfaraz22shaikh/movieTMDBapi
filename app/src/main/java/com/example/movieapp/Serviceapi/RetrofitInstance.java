package com.example.movieapp.Serviceapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    // acts as a central configuration point for
    // defining how HTTP request and responses
    // should be handle

    static Retrofit retrofit = null;
    static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieApiService getServies(){
        if( retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieApiService.class);
    }
}
