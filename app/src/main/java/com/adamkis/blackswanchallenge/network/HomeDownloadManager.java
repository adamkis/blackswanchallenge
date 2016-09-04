package com.adamkis.blackswanchallenge.network;

import android.support.annotation.Nullable;

import com.adamkis.blackswanchallenge.MyApplication;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.model.response.MovieSearchResponse;
import com.adamkis.blackswanchallenge.model.response.PeopleSearchResponse;
import com.adamkis.blackswanchallenge.model.response.TvShowSearchResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by akis on 30/08/16.
 */
public class HomeDownloadManager {

    HomeDownloadResponseListener homeDownloadResponseListener;

    public static enum DownloadMode { SEARCH, POPULAR };

    public HomeDownloadManager(HomeDownloadResponseListener homeDownloadResponseListener) {
        this.homeDownloadResponseListener = homeDownloadResponseListener;
    }

    private void downloadPopularMovies(){
        homeDownloadResponseListener.showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
            Const.buildPopularMovieRequestUrl(),
            MovieSearchResponse.class,
            null,
            new Response.Listener<MovieSearchResponse>() {
                @Override
                public void onResponse(MovieSearchResponse popularMovieResponse) {
                    homeDownloadResponseListener.showMoviesResponse(popularMovieResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    homeDownloadResponseListener.showError(error);
                }
            }
        );
        VolleySingleton.get(MyApplication.getAppContext()).addToRequestQueue(popularMovieRequest);
    }

    private void searchMovies(@Nullable String searchKeyword){
        homeDownloadResponseListener.showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
            Const.buildSearchMovieUrl(searchKeyword),
            MovieSearchResponse.class,
            null,
            new Response.Listener<MovieSearchResponse>() {
                @Override
                public void onResponse(MovieSearchResponse popularMovieResponse) {
                    homeDownloadResponseListener.showMoviesResponse(popularMovieResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    homeDownloadResponseListener.showError(error);
                }
            }
        );
        VolleySingleton.get(MyApplication.getAppContext()).addToRequestQueue(popularMovieRequest);
    }

    private void downloadPopularTvShows(){
        homeDownloadResponseListener.showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
            Const.buildPopularTvShowsRequestUrl(),
            TvShowSearchResponse.class,
            null,
            new Response.Listener<TvShowSearchResponse>() {
                @Override
                public void onResponse(TvShowSearchResponse tvShowSearchResponse) {
                    homeDownloadResponseListener.showTvShowsResponse(tvShowSearchResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    homeDownloadResponseListener.showError(error);
                }
            }
        );
        VolleySingleton.get(MyApplication.getAppContext()).addToRequestQueue(popularMovieRequest);
    }

    private void searchTvShows(@Nullable String searchKeyword){
        homeDownloadResponseListener.showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
            Const.buildSearchTVUrl(searchKeyword),
            TvShowSearchResponse.class,
            null,
            new Response.Listener<TvShowSearchResponse>() {
                @Override
                public void onResponse(TvShowSearchResponse tvShowSearchResponse) {
                    homeDownloadResponseListener.showTvShowsResponse(tvShowSearchResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    homeDownloadResponseListener.showError(error);
                }
            }
        );
        VolleySingleton.get(MyApplication.getAppContext()).addToRequestQueue(popularMovieRequest);
    }

    private void downloadPopularPeople(){
        homeDownloadResponseListener.showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
            Const.buildPopularPeopleRequestUrl(),
            PeopleSearchResponse.class,
            null,
            new Response.Listener<PeopleSearchResponse>() {
                @Override
                public void onResponse(PeopleSearchResponse peopleSearchResponse) {
                    homeDownloadResponseListener.showPeopleResponse(peopleSearchResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    homeDownloadResponseListener.showError(error);
                }
            }
        );
        VolleySingleton.get(MyApplication.getAppContext()).addToRequestQueue(popularMovieRequest);
    }

    private void searchPeople(@Nullable String searchKeyword){
        homeDownloadResponseListener.showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
            Const.buildSearchPeopleUrl(searchKeyword),
            PeopleSearchResponse.class,
            null,
            new Response.Listener<PeopleSearchResponse>() {
                @Override
                public void onResponse(PeopleSearchResponse peopleSearchResponse) {
                    homeDownloadResponseListener.showPeopleResponse(peopleSearchResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    homeDownloadResponseListener.showError(error);
                }
            }
        );
        VolleySingleton.get(MyApplication.getAppContext()).addToRequestQueue(popularMovieRequest);
    }

    public void downloadData(int categoryIndex, DownloadMode downloadMode, @Nullable String keyword){
        switch (categoryIndex){
            case Const.MOVIES_CATEGORY_INDEX:
                if( downloadMode == DownloadMode.POPULAR ) {
                    downloadPopularMovies();
                }
                else if( downloadMode == DownloadMode.SEARCH ){
                    searchMovies(keyword);
                }
                break;
            case Const.TV_SHOWS_CATEGORY_INDEX:
                if( downloadMode == DownloadMode.POPULAR ) {
                    downloadPopularTvShows();
                }
                else if( downloadMode == DownloadMode.SEARCH ){
                    searchTvShows(keyword);
                }
                break;
            case Const.PEOPLE_CATEGORY_INDEX:
                if( downloadMode == DownloadMode.POPULAR ) {
                    downloadPopularPeople();
                }
                else if( downloadMode == DownloadMode.SEARCH ){
                    searchPeople(keyword);
                }
                break;
        }
    }


}
