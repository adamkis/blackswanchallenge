package com.adamkis.blackswanchallenge.network;

import com.adamkis.blackswanchallenge.MyApplication;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.model.response.MovieSearchResponse;
import com.adamkis.blackswanchallenge.model.response.TvShowSearchResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by akis on 30/08/16.
 */
public class HomeDownloadManager {

    HomeDownloadResponseListener homeDownloadResponseListener;

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

    public void downloadPopular(int categoryIndex){
        switch (categoryIndex){
            case Const.MOVIES_CATEGORY_INDEX:
                downloadPopularMovies();
                break;
            case Const.TV_SHOWS_CATEGORY_INDEX:
                downloadPopularTvShows();
                break;
            case Const.PEOPLE_CATEGORY_INDEX:
                break;
        }
    }


}
