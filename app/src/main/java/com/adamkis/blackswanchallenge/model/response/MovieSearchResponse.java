package com.adamkis.blackswanchallenge.model.response;

import com.adamkis.blackswanchallenge.model.Movie;
import com.adamkis.blackswanchallenge.model.response.BaseScreenResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akis on 30/08/16.
 */
public class MovieSearchResponse extends BaseScreenResponse {

    @Expose @SerializedName("results")
    private List<Movie> results = new ArrayList<>();

    public List<Movie> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "page='" + getPage() + '\'' +
                ", totalResults='" + getTotalResults() + '\'' +
                ", totalPages='" + getTotalPages() + '\'' +
                ", results=" + results +
                '}';
    }

}
