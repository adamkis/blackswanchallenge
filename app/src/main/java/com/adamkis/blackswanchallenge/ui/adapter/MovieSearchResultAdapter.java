package com.adamkis.blackswanchallenge.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamkis.blackswanchallenge.MyApplication;
import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.model.Movie;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.adamkis.blackswanchallenge.ui.activity.DetailActivity;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


/**
 * Created by akis on 30/08/16.
 */

public class MovieSearchResultAdapter extends RecyclerView.Adapter<MovieSearchResultAdapter.ViewHolder>{

    private List<Movie> dataSet;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView tvTitle;
        public NetworkImageView image;
        public ViewHolder(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.image = (NetworkImageView) v.findViewById(R.id.image);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(Const.KEY_MOVIE_OBJECT, dataSet.get(getAdapterPosition()));
            context.startActivity(intent);
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
        v.setOnClickListener(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(dataSet.get(position).getTitle());
        holder.image.setDefaultImageResId(R.color.imagePlaceholderColor);
        holder.image.setImageUrl(Const.buildImagePath(dataSet.get(position).getPosterPath()),
                VolleySingleton.get(MyApplication.getAppContext()).getImageLoader());
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