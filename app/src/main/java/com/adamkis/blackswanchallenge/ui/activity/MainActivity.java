package com.adamkis.blackswanchallenge.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.common.Utils;
import com.adamkis.blackswanchallenge.model.TvShow;
import com.adamkis.blackswanchallenge.model.response.MovieSearchResponse;
import com.adamkis.blackswanchallenge.model.response.TvShowSearchResponse;
import com.adamkis.blackswanchallenge.network.GsonRequest;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.adamkis.blackswanchallenge.ui.adapter.MovieSearchResultAdapter;
import com.adamkis.blackswanchallenge.ui.adapter.TvShowSearchResultAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemSelectedListener {

    private CoordinatorLayout clRoot;
    private MovieSearchResultAdapter movieAdapter;
    private TvShowSearchResultAdapter tvAdapter;
    private RecyclerView searchResultContainer;
    private SwipeRefreshLayout swipeRefreshContainer;
    private LinearLayoutManager mLayoutManager;
    private Spinner spCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the results container and download data
        clRoot = (CoordinatorLayout) findViewById(R.id.clRoot);
        searchResultContainer = (RecyclerView) findViewById(R.id.searchResultContainer);
        swipeRefreshContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshContainer);
        searchResultContainer.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        searchResultContainer.setLayoutManager(mLayoutManager);
        swipeRefreshContainer.setOnRefreshListener(this);
        movieAdapter = new MovieSearchResultAdapter();
        searchResultContainer.setAdapter(movieAdapter);
        downloadPopularMovies();

        // Setup the category selector
        spCategory = (Spinner) findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.home_screen_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setOnItemSelectedListener(this);

    }

    private void downloadPopularMovies(){
        showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
                Const.buildPopularMovieRequestUrl(),
                MovieSearchResponse.class,
                null,
                new Response.Listener<MovieSearchResponse>() {
                    @Override
                    public void onResponse(MovieSearchResponse popularMovieResponse) {
                        showLoading(false);
                        searchResultContainer.setAdapter(movieAdapter);
                        movieAdapter.showData(popularMovieResponse.getResults());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error);
                    }
                }
        );
        VolleySingleton.get(this).addToRequestQueue(popularMovieRequest);
    }

    private void downloadPopularTvShows(){
        showLoading(true);
        GsonRequest popularMovieRequest = new GsonRequest(
                Const.buildPopularTvShowsRequestUrl(),
                TvShowSearchResponse.class,
                null,
                new Response.Listener<TvShowSearchResponse>() {
                    @Override
                    public void onResponse(TvShowSearchResponse tvShowSearchResponse) {
                        showLoading(false);
                        tvAdapter = new TvShowSearchResultAdapter();
                        searchResultContainer.setAdapter(tvAdapter);
                        tvAdapter.showData(tvShowSearchResponse.getResults());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError(error);
                    }
                }
        );
        VolleySingleton.get(this).addToRequestQueue(popularMovieRequest);
    }

    private void downloadSelectedCategory(int index){
        switch (index){
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

    @Override
    public void onRefresh() {
        downloadSelectedCategory(spCategory.getSelectedItemPosition());
    }

    private void showLoading(final boolean show){
        if( show ){
            if( movieAdapter != null ) {
                movieAdapter.clearData();
            }
            if( tvAdapter != null ) {
                tvAdapter.clearData();
            }
        }
        swipeRefreshContainer.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshContainer.setRefreshing(show);
            }
        });
    }

    private void showError(VolleyError error){
        Utils.log(error.toString());
        showLoading(false);
        final Snackbar snackbar = Snackbar
                .make(clRoot, getString(R.string.loading_failed), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        downloadSelectedCategory(spCategory.getSelectedItemPosition());
                    }
                });
        snackbar.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        downloadSelectedCategory(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
