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
import com.adamkis.blackswanchallenge.model.response.MovieSearchResponse;
import com.adamkis.blackswanchallenge.model.response.TvShowSearchResponse;
import com.adamkis.blackswanchallenge.network.GsonRequest;
import com.adamkis.blackswanchallenge.network.HomeDownloadManager;
import com.adamkis.blackswanchallenge.network.HomeDownloadResponseListener;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.adamkis.blackswanchallenge.ui.adapter.MovieSearchResultAdapter;
import com.adamkis.blackswanchallenge.ui.adapter.TvShowSearchResultAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class HomeActivity
        extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener,
            AdapterView.OnItemSelectedListener,
            HomeDownloadResponseListener {

    private CoordinatorLayout clRoot;
    private MovieSearchResultAdapter movieAdapter;
    private TvShowSearchResultAdapter tvAdapter;
    private RecyclerView searchResultContainer;
    private SwipeRefreshLayout swipeRefreshContainer;
    private LinearLayoutManager mLayoutManager;
    private Spinner spCategory;
    private HomeDownloadManager homeDownloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Setup the results container
        clRoot = (CoordinatorLayout) findViewById(R.id.clRoot);
        searchResultContainer = (RecyclerView) findViewById(R.id.searchResultContainer);
        swipeRefreshContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshContainer);
        searchResultContainer.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        searchResultContainer.setLayoutManager(mLayoutManager);
        swipeRefreshContainer.setOnRefreshListener(this);
        movieAdapter = new MovieSearchResultAdapter();
        searchResultContainer.setAdapter(movieAdapter);

        // Setup the category selector
        spCategory = (Spinner) findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.home_screen_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setOnItemSelectedListener(this);

        // Download data
        homeDownloadManager = new HomeDownloadManager(this);
        homeDownloadManager.downloadSelectedCategory(spCategory.getSelectedItemPosition());

    }

    @Override
    public void onRefresh() {
        homeDownloadManager.downloadSelectedCategory(spCategory.getSelectedItemPosition());
    }

    @Override
    public void showLoading(final boolean show){
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

    @Override
    public void showError(VolleyError error){
        Utils.log(error.toString());
        showLoading(false);
        final Snackbar snackbar = Snackbar
                .make(clRoot, getString(R.string.loading_failed), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeDownloadManager.downloadSelectedCategory(spCategory.getSelectedItemPosition());
                    }
                });
        snackbar.show();
    }

    @Override
    public void showMoviesResponse(MovieSearchResponse popularMovieResponse) {
        showLoading(false);
        searchResultContainer.setAdapter(movieAdapter);
        movieAdapter.showData(popularMovieResponse.getResults());
    }

    @Override
    public void showTvShowsResponse(TvShowSearchResponse tvShowSearchResponse) {
        showLoading(false);
        tvAdapter = new TvShowSearchResultAdapter();
        searchResultContainer.setAdapter(tvAdapter);
        tvAdapter.showData(tvShowSearchResponse.getResults());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        homeDownloadManager.downloadSelectedCategory(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
