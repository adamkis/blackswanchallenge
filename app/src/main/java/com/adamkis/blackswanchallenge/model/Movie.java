package com.adamkis.blackswanchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akis on 30/08/16.
 */
public class Movie {

    @Expose @SerializedName("poster_path")
    private String posterPath;

    @Expose @SerializedName("adult")
    private boolean adult;

    @Expose @SerializedName("overview")
    private String overview;

    @Expose @SerializedName("release_date")
    private String releaseDate;

    @Expose @SerializedName("id")
    private int id;

    @Expose @SerializedName("original_title")
    private String originalTitle;

    @Expose @SerializedName("original_language")
    private String originalLanguage;

    @Expose @SerializedName("title")
    private String title;

    @Expose @SerializedName("backdrop_path")
    private String backdropPath;

    @Expose @SerializedName("popularity")
    private float popularity;

    @Expose @SerializedName("vote_count")
    private int voteCount;

    @Expose @SerializedName("video")
    private boolean video;

    @Expose @SerializedName("vote_average")
    private float voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "posterPath='" + posterPath + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", title='" + title + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", popularity=" + popularity +
                ", voteCount=" + voteCount +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                '}';
    }

}
