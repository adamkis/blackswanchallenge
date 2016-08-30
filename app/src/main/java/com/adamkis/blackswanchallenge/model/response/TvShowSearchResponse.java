package com.adamkis.blackswanchallenge.model.response;

import com.adamkis.blackswanchallenge.model.Movie;
import com.adamkis.blackswanchallenge.model.TvShow;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akis on 30/08/16.
 */
public class TvShowSearchResponse extends BaseScreenResponse {

    @Expose @SerializedName("results")
    private List<TvShow> results = new ArrayList<>();

    public List<TvShow> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "TvShowSearchResponse{" +
                "page='" + getPage() + '\'' +
                ", totalResults='" + getTotalResults() + '\'' +
                ", totalPages='" + getTotalPages() + '\'' +
                ", results=" + results +
                '}';
    }

}
