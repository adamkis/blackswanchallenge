package com.adamkis.blackswanchallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.model.Movie;

import java.util.List;


/**
 * Created by akis on 30/08/16.
 */

public class MovieSearchResultAdapter extends RecyclerView.Adapter<MovieSearchResultAdapter.ViewHolder> {

    private List<Movie> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ViewHolder(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        }
    }

    public MovieSearchResultAdapter() {}

    public void showData(List<Movie> dataSet){
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public MovieSearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(dataSet.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if( this.dataSet == null ) {
            return 0;
        }
        return dataSet.size();
    }

    public void clearData() {
        if( this.dataSet != null ) {
            this.dataSet.clear();
        }
        notifyDataSetChanged();
    }

}