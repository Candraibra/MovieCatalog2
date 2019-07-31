package com.candraibra.moviecatalog2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CategoryViewHolder> {
    MovieAdapter(Context context) {
        Context context1 = context;
    }

    private ArrayList<Movie> getListmovie() {
        return listmovie;
    }

    void setListmovie(ArrayList<Movie> listmovie) {
        this.listmovie = listmovie;
    }

    private ArrayList<Movie> listmovie;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_movie, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tvTitle.setText(getListmovie().get(position).getTitle());
        holder.tvDescription.setText(getListmovie().get(position).getDescription());
        holder.imgPhoto.setImageResource(getListmovie().get(position).getPoster());
    }

    @Override
    public int getItemCount() {
        return getListmovie().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView imgPhoto;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            imgPhoto = itemView.findViewById(R.id.img_poster);
        }
    }
}