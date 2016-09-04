package com.adamkis.blackswanchallenge.model.response;

import com.adamkis.blackswanchallenge.model.Person;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akis on 30/08/16.
 */
public class PeopleSearchResponse extends BaseScreenResponse {

    @Expose @SerializedName("results")
    private List<Person> results = new ArrayList<>();

    public List<Person> getResults() {
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
