package com.example.movieapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ListItemLayoutBinding;
import com.example.movieapp.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.movieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(ArrayList<Movie> movieArrayList, Context context) {
        this.movieArrayList = movieArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context) ,
                R.layout.list_item_layout,
                parent,
                false
        );
        return new movieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull movieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.listItemLayoutBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public  class movieViewHolder extends RecyclerView.ViewHolder{
        private ListItemLayoutBinding listItemLayoutBinding;

        public movieViewHolder(ListItemLayoutBinding listItemLayoutBinding) {
            super(listItemLayoutBinding.getRoot());
            this.listItemLayoutBinding = listItemLayoutBinding;

            listItemLayoutBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

    }
}
