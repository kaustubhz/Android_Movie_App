package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movielist.controller.Api;
import com.example.movielist.controller.ApiValues;
import com.example.movielist.model.MovieDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = findViewById(R.id.myMovieList);

//        To create a REST client
        Retrofit myRestClient = new Retrofit.Builder().baseUrl(ApiValues.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api apiInterface = myRestClient.create(Api.class);

        Call<MovieDetails> myCall = apiInterface.getAllMovies("popular",ApiValues.API_KEY);
        myCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails movieDetails=response.body();
                Log.d("Total count", "onResponse: "+movieDetails.getTotal_results());
                List<MovieDetails.ResultsBean> movieList=movieDetails.getResults();
                MyAdapter adapter=new MyAdapter(movieList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
            t.printStackTrace();
            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}