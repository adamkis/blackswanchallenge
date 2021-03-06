package com.adamkis.blackswanchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akis on 30/08/16.
 */
public class TvShow extends BaseScreenObject {

    @Expose @SerializedName("first_air_date")
    private boolean firstAirDate;

    @Expose @SerializedName("original_name")
    private String originalName;

    @Expose @SerializedName("name")
    private String name;

    public boolean isFirstAirDate() {
        return firstAirDate;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TvShow{" +
                "name='" + name + '\'' +
                "originalName='" + originalName + '\'' +
                "firstAirDate='" + firstAirDate + '\'' +
                ", posterPath='" + getPosterPath() + '\'' +
                ", overview='" + getOverview() + '\'' +
                ", id=" + getId() +
                ", originalLanguage='" + getOriginalLanguage() + '\'' +
                ", backdropPath='" + getBackdropPath() + '\'' +
                ", popularity=" + getPopularity() +
                ", voteCount=" + getVoteCount() +
                ", voteAverage=" + getVoteAverage() +
                '}';
    }

}
