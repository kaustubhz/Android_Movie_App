package com.example.movielist.controller;

import com.example.movielist.model.MovieDetails;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

//    To list all popular movies
    @GET("movie/{category}")
    Call<MovieDetails> getAllMovies(@Path("category") String category,@Query("api_key") String apiKey);
}
