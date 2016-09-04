package com.adamkis.blackswanchallenge.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adamkis.blackswanchallenge.MyApplication;
import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.model.Person;
import com.adamkis.blackswanchallenge.model.TvShow;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;


/**
 * Created by akis on 30/08/16.
 */

public class PeopleSearchResultAdapter extends RecyclerView.Adapter<PeopleSearchResultAdapter.ViewHolder> {

    private List<Person> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public NetworkImageView image;
        public ViewHolder(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.image = (NetworkImageView) v.findViewById(R.id.image);
        }
    }

    public PeopleSearchResultAdapter() {}

    public void showData(List<Person> dataSet){
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public PeopleSearchResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(dataSet.get(position).getName());
        holder.image.setDefaultImageResId(R.color.imagePlaceholderColor);
        holder.image.setImageUrl(Const.buildImagePath(dataSet.get(position).getProfilePath()),
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