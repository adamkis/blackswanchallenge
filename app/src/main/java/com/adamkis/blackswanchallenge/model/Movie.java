package com.adamkis.blackswanchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akis on 30/08/16.
 */
public class Movie extends BaseScreenObject implements Serializable {

    @Expose @SerializedName("adult")
    private boolean adult;

    @Expose @SerializedName("release_date")
    private String releaseDate;

    @Expose @SerializedName("original_title")
    private String originalTitle;

    @Expose @SerializedName("title")
    private String title;

    @Expose @SerializedName("video")
    private boolean video;

    public boolean isAdult() {
        return adult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "posterPath='" + getPosterPath() + '\'' +
                ", adult=" + adult +
                ", overview='" + getOverview() + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", id=" + getId() +
                ", originalTitle='" + originalTitle + '\'' +
                ", originalLanguage='" + getOriginalLanguage() + '\'' +
                ", title='" + title + '\'' +
                ", backdropPath='" + getBackdropPath() + '\'' +
                ", popularity=" + getPopularity() +
                ", voteCount=" + getVoteCount() +
                ", video=" + video +
                ", voteAverage=" + getVoteAverage() +
                '}';
    }

}
