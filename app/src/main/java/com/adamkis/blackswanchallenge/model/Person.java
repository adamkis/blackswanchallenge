package com.adamkis.blackswanchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akis on 30/08/16.
 */
public class Person {

    @Expose @SerializedName("name")
    private String name;

    @Expose @SerializedName("id")
    private int id;

    @Expose @SerializedName("adult")
    private boolean adult;

    @Expose @SerializedName("popularity")
    private float popularity;

    @Expose
    @SerializedName("profile_path")
    private String profilePath;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", adult=" + adult +
                ", popularity=" + popularity +
                ", profilePath='" + profilePath + '\'' +
                '}';
    }
}
