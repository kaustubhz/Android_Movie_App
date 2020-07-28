package com.example.movielist;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.controller.ApiValues;
import com.example.movielist.model.MovieDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //    To store movie list
    List<MovieDetails.ResultsBean> movies;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public final ImageView movieImage;
        public final TextView movieName;
        public final TextView movieType;
        public final TextView movieYear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieName = (TextView) itemView.findViewById(R.id.movie_name);
            movieType = (TextView) itemView.findViewById(R.id.movie_type);
            movieYear = (TextView) itemView.findViewById(R.id.movie_year);
        }
    }

    public MyAdapter(List<MovieDetails.ResultsBean> movies) {
        this.movies = movies;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

//        Inflate the custom layout
        View movieView = layoutInflater.inflate(R.layout.item_movie, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
// Get the data based on position
        MovieDetails.ResultsBean currentMovie = movies.get(position);

//        Set items based on views and data models
        TextView textView1 = holder.movieName;
        textView1.setText(currentMovie.getTitle());

        TextView textView2 = holder.movieType;
        /*if (currentMovie.isAdult()) {
            textView2.setText("A+");
        } else {
            textView2.setText("Not A+");
        }*/
        textView2.setText(currentMovie.isAdult()?"A+":"Not A+");

        TextView textView3 = holder.movieYear;
        textView3.setText(currentMovie.getRelease_date());

        ImageView imageView = holder.movieImage;
        Log.d("image", "onBindViewHolder: " + ApiValues.IMAGE_URL + currentMovie.getPoster_path());
        Picasso.get().load(ApiValues.IMAGE_URL + currentMovie.getPoster_path()).resize(900, 300).into(imageView);
//        Picasso.get().load(R.drawable.endgame).into(imageView);
//        imageView.setImageURI(Uri.parse(currentMovie.getFlag()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
