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
public class TVFragment extends Fragment {
    private String[] dataTitletv;
    private String[] dataDescriptiontv;
    private TypedArray dataPostertv;
    private TvshowAdapter adaptertv;
    private ArrayList<Tvshow> tvshows = new ArrayList<>();

    public TVFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_tv_show, container, false);
        RecyclerView rvCategory = fragmentView.findViewById(R.id.rv_category_tv);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adaptertv = new TvshowAdapter(getActivity());
        rvCategory.setAdapter(adaptertv);
        adaptertv.setListtv(tvshows);
        rvCategory.setHasFixedSize(true);
        prepare();
        addItem();
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TV, tvshows.get(position));
                startActivity(intent);
            }
        });
        return fragmentView;
    }

    private void prepare() {
        dataTitletv = getResources().getStringArray(R.array.data_title_tv);
        dataDescriptiontv = getResources().getStringArray(R.array.data_description_tv);
        dataPostertv = getResources().obtainTypedArray(R.array.data_poster_tv);
    }

    private void addItem() {

        for (int i = 0; i < dataTitletv.length; i++) {
            Tvshow tvshow = new Tvshow();
            tvshow.setPoster(dataPostertv.getResourceId(i, -1));
            tvshow.setTitle(dataTitletv[i]);
            tvshow.setDescription(dataDescriptiontv[i]);
            tvshows.add(tvshow);
        }
        adaptertv.setListtv(tvshows);
    }

}
