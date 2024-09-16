package com.example.movieapp.model;
import com.example.movieapp.model.Result;


import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.example.movieapp.R;
import com.example.movieapp.Serviceapi.MovieApiService;
import com.example.movieapp.Serviceapi.RetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.movieapp.model.Result;



public class MovieRepository {
    // used to abstract the data source details and
    // provides a clean API for the ViewModel to
    // fetch and manage data

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }
    public MutableLiveData<List<Movie>> getMutableLiveData() {
        MovieApiService movieApiService = RetrofitInstance.getServies(); // Corrected method name

        Call<Result> call = movieApiService.getPopularMovies(application.getApplicationContext()
                .getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result != null && result.getResults() != null) {
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                // Handle failure
            }
        });
        return mutableLiveData;
    }

}
