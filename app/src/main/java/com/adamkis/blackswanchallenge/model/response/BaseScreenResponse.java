package com.adamkis.blackswanchallenge.model.response;

import com.adamkis.blackswanchallenge.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akis on 30/08/16.
 */
public abstract class BaseScreenResponse {

    @Expose
    @SerializedName("page")
    private String page;

    @Expose @SerializedName("total_results")
    private String totalResults;

    @Expose @SerializedName("total_pages")
    private String totalPages;

    public String getPage() {
        return page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "BaseScreenResponse{" +
                "page='" + page + '\'' +
                ", totalResults='" + totalResults + '\'' +
                ", totalPages='" + totalPages + '\'' +
                '}';
    }

}
