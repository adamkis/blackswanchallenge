package com.adamkis.blackswanchallenge.network;

import com.adamkis.blackswanchallenge.model.response.MovieSearchResponse;
import com.adamkis.blackswanchallenge.model.response.PeopleSearchResponse;
import com.adamkis.blackswanchallenge.model.response.TvShowSearchResponse;
import com.android.volley.VolleyError;

/**
 * Created by akis on 30/08/16.
 */
public interface HomeDownloadResponseListener {
    void showLoading(boolean show);
    void showError(VolleyError error);
    void showMoviesResponse(MovieSearchResponse popularMovieResponse);
    void showTvShowsResponse(TvShowSearchResponse tvShowSearchResponse);
    void showPeopleResponse(PeopleSearchResponse peopleSearchResponse);
}
