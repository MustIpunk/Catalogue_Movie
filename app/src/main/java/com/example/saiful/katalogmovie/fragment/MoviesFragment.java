package com.example.saiful.katalogmovie.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.saiful.katalogmovie.Movies;
import com.example.saiful.katalogmovie.R;
import com.example.saiful.katalogmovie.ViewModel.MoviesViewModel;
import com.example.saiful.katalogmovie.adapter.MoviesAdapter;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private MoviesAdapter adapter;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;

    public MoviesFragment() {
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new MoviesAdapter();
        View view = inflater.inflate(R.layout.activity_movies_fragment,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovies().observe(this, getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }
    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies) {
            if (movies != null) {
                adapter.setData(movies);
            }

            showLoading(false);

        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
