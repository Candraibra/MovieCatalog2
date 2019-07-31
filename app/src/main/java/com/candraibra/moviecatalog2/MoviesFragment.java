package com.candraibra.moviecatalog2;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataDescription;
    private TypedArray dataPoster;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_movies, container, false);
        RecyclerView rvCategory = fragmentView.findViewById(R.id.rv_category);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieAdapter(getActivity());
        rvCategory.setAdapter(adapter);
        adapter.setListmovie(movies);
        rvCategory.setHasFixedSize(true);
        prepare();
        addItem();
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE, movies.get(position));
                startActivity(intent);
            }
        });
        return fragmentView;
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.data_title);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPoster = getResources().obtainTypedArray(R.array.data_poster);
    }

    private void addItem() {

        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setPoster(dataPoster.getResourceId(i, -1));
            movie.setTitle(dataTitle[i]);
            movie.setDescription(dataDescription[i]);
            movies.add(movie);
        }
        adapter.setListmovie(movies);
    }

}
