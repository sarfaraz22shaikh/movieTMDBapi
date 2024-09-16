package com.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.model.Movie;
import com.example.movieapp.view.MovieAdapter;
import com.example.movieapp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel activityViewModel;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize data binding
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Set window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.swipe_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize ViewModel
        activityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        getPopularMovies();

        // Swipe to refresh
        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeColors(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(() -> getPopularMovies());
    }

    private void getPopularMovies() {
        activityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesfromlivedata) {
                movies = (ArrayList<Movie>) moviesfromlivedata;
                DisplayMoviesInRecycleView();
            }

            private void DisplayMoviesInRecycleView() {
                recyclerView = activityMainBinding.recyclerView;

                // Use MainActivity.this to refer to the correct context
                movieAdapter = new MovieAdapter(movies, MainActivity.this);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(movieAdapter);

                // Use MainActivity.this for the GridLayoutManager as well
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

                // Notify the adapter that the dataset has changed
                movieAdapter.notifyDataSetChanged();
            }
        });
    }
}
