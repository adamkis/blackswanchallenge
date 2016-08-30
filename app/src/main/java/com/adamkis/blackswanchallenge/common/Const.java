package com.adamkis.blackswanchallenge.common;

/**
 * Created by akis on 30/08/16.
 */
public class Const {

//    https://api.themoviedb.org/3/movie/550?api_key=###

    public static String MOVIEDB_URL_BASE = "https://api.themoviedb.org/3/";
    public static String MOVIEDB_MOVIE_POPULAR_ENDPOINT = "movie/popular";
    public static String MOVIEDB_API_KEY = "?api_key=0a08e38b874d0aa2d426ffc04357069d";

    public static String buildPopularMovieRequestUrl(){
        return new StringBuilder(MOVIEDB_URL_BASE)
            .append(MOVIEDB_MOVIE_POPULAR_ENDPOINT)
            .append(MOVIEDB_API_KEY)
            .toString();
    }

}
