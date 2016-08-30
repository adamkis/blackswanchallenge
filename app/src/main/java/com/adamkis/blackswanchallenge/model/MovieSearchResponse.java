package com.adamkis.blackswanchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akis on 30/08/16.
 */
public class MovieSearchResponse {

    @Expose @SerializedName("page")
    private String page;

    @Expose @SerializedName("total_results")
    private String totalResults;

    @Expose @SerializedName("total_pages")
    private String totalPages;

    @Expose @SerializedName("results")
    private List<Movie> results = new ArrayList<>();

    public String getPage() {
        return page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "page='" + page + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", totalPages='" + totalPages + '\'' +
                ", results=" + results +
                '}';
    }

}
