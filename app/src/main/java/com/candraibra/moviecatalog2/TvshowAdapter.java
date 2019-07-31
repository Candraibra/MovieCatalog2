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

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.CategoryViewHolder> {
    public TvshowAdapter(Context context) {
        this.context = context;
    }

    private final Context context;

    public ArrayList<Tvshow> getListtv() {
        return listtv;
    }

    public void setListtv(ArrayList<Tvshow> listtv) {
        this.listtv = listtv;
    }

    private ArrayList<Tvshow> listtv;

    @NonNull
    @Override
    public TvshowAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_tv, parent, false);
        return new TvshowAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull TvshowAdapter.CategoryViewHolder holder, int position) {
        holder.tvTitle.setText(getListtv().get(position).getTitle());
        holder.tvDescription.setText(getListtv().get(position).getDescription());
        holder.imgPhoto.setImageResource(getListtv().get(position).getPoster());
    }

    @Override
    public int getItemCount() {
        return getListtv().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView imgPhoto;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title_tv);
            tvDescription = itemView.findViewById(R.id.tv_item_description_tv);
            imgPhoto = itemView.findViewById(R.id.img_poster_tv);
        }
    }
}
