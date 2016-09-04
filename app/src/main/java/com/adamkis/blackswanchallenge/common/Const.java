package com.adamkis.blackswanchallenge.common;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by akis on 30/08/16.
 */
public class Const {

    public static final long SEARCH_REVEAL_ANIMATION_SPEED = 500;

    public static final int MOVIES_CATEGORY_INDEX = 0;
    public static final int TV_SHOWS_CATEGORY_INDEX = 1;
    public static final int PEOPLE_CATEGORY_INDEX = 2;

    public static final String MOVIEDB_URL_BASE = "https://api.themoviedb.org/3/";
    public static final String MOVIEDB_MOVIE = "movie";
    public static final String MOVIEDB_SEARCH = "search";
    public static final String MOVIEDB_TV = "tv";
    public static final String MOVIEDB_PERSON = "person";
    public static final String MOVIEDB_POPULAR = "popular";
    public static final String MOVIEDB_API_KEY = "?api_key=0a08e38b874d0aa2d426ffc04357069d";
    public static final String MOVIEDB_QUERY = "&query=";
    public static final String SEPARATOR = "/";
    public static final String MOVIEDB_IMAGE_URL_BASE = "http://image.tmdb.org/t/p/";
    public static final String MOVIEDB_IMAGE_SIZE_W300 = "w300/";

    public static String buildSearchMovieUrl(String keyword){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_SEARCH)
            .append(SEPARATOR)
            .append(MOVIEDB_MOVIE)
            .append(MOVIEDB_API_KEY)
            .append(MOVIEDB_QUERY)
            .append(Uri.encode(keyword))
            .toString();
    }

    public static String buildSearchTVUrl(String keyword){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_SEARCH)
            .append(SEPARATOR)
            .append(MOVIEDB_TV)
            .append(MOVIEDB_API_KEY)
            .append(MOVIEDB_QUERY)
            .append(Uri.encode(keyword))
            .toString();
    }

    public static String buildSearchPeopleUrl(String keyword){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_SEARCH)
            .append(SEPARATOR)
            .append(MOVIEDB_PERSON)
            .append(MOVIEDB_API_KEY)
            .append(MOVIEDB_QUERY)
            .append(Uri.encode(keyword))
            .toString();
    }

    public static String buildPopularTvShowsRequestUrl(){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_TV)
            .append(SEPARATOR)
            .append(MOVIEDB_POPULAR)
            .append(MOVIEDB_API_KEY)
            .toString();
    }

    public static String buildPopularPeopleRequestUrl(){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_PERSON)
            .append(SEPARATOR)
            .append(MOVIEDB_POPULAR)
            .append(MOVIEDB_API_KEY)
            .toString();
    }

    public static String buildPopularMovieRequestUrl(){
        return new StringBuilder(MOVIEDB_URL_BASE)
                .append(MOVIEDB_MOVIE)
                .append(SEPARATOR)
                .append(MOVIEDB_POPULAR)
                .append(MOVIEDB_API_KEY)
                .toString();
    }

    public static String buildImagePath( @NonNull String filePath){
        return new StringBuilder(MOVIEDB_IMAGE_URL_BASE)
                .append(MOVIEDB_IMAGE_SIZE_W300)
                .append(filePath)
                .toString();
    }

}
