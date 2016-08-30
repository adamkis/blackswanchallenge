package com.adamkis.blackswanchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akis on 30/08/16.
 */
public class BaseScreenObject {

    @Expose
    @SerializedName("poster_path")
    private String posterPath;

    @Expose @SerializedName("vote_average")
    private float voteAverage;

    @Expose @SerializedName("backdrop_path")
    private String backdropPath;

    @Expose @SerializedName("overview")
    private String overview;

    @Expose @SerializedName("id")
    private int id;

    @Expose @SerializedName("popularity")
    private float popularity;

    @Expose @SerializedName("original_language")
    private String originalLanguage;

    @Expose @SerializedName("vote_count")
    private int voteCount;

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getId() {
        return id;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return "BaseScreenObject{" +
                "posterPath='" + posterPath + '\'' +
                ", voteAverage=" + voteAverage +
                ", backdropPath='" + backdropPath + '\'' +
                ", overview='" + overview + '\'' +
                ", id=" + id +
                ", popularity=" + popularity +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
