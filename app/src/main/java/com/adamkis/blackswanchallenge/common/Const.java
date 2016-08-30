package com.adamkis.blackswanchallenge.common;

/**
 * Created by akis on 30/08/16.
 */
public class Const {

    public static final long SEARCH_REVEAL_ANIMATION_SPEED = 500;

    public static final int MOVIES_CATEGORY_INDEX = 0;
    public static final int TV_SHOWS_CATEGORY_INDEX = 1;
    public static final int PEOPLE_CATEGORY_INDEX = 2;

    public static String MOVIEDB_URL_BASE = "https://api.themoviedb.org/3/";
    public static String MOVIEDB_MOVIE_ENDPOINT = "movie/";
    public static String MOVIEDB_TV_ENDPOINT = "tv/";
    public static String MOVIEDB_POPULAR = "popular";
    public static String MOVIEDB_API_KEY = "?api_key=0a08e38b874d0aa2d426ffc04357069d";

    public static String buildPopularTvShowsRequestUrl(){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_TV_ENDPOINT)
            .append(MOVIEDB_POPULAR)
            .append(MOVIEDB_API_KEY)
            .toString();
    }

    public static String buildPopularMovieRequestUrl(){
        return new StringBuilder(MOVIEDB_URL_BASE)
                .append(MOVIEDB_MOVIE_ENDPOINT)
                .append(MOVIEDB_POPULAR)
                .append(MOVIEDB_API_KEY)
                .toString();
    }

}
