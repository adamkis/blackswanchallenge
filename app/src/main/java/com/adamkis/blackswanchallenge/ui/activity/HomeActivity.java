package com.adamkis.blackswanchallenge.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.common.Utils;
import com.adamkis.blackswanchallenge.model.response.MovieSearchResponse;
import com.adamkis.blackswanchallenge.model.response.TvShowSearchResponse;
import com.adamkis.blackswanchallenge.network.HomeDownloadManager;
import com.adamkis.blackswanchallenge.network.HomeDownloadResponseListener;
import com.adamkis.blackswanchallenge.ui.adapter.MovieSearchResultAdapter;
import com.adamkis.blackswanchallenge.ui.adapter.TvShowSearchResultAdapter;
import com.android.volley.VolleyError;

public class HomeActivity
        extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener,
            AdapterView.OnItemSelectedListener,
            HomeDownloadResponseListener,
            View.OnClickListener,
            TextView.OnEditorActionListener {

    private CoordinatorLayout clRoot;
    private MovieSearchResultAdapter movieAdapter;
    private TvShowSearchResultAdapter tvAdapter;
    private RecyclerView searchResultContainer;
    private SwipeRefreshLayout swipeRefreshContainer;
    private LinearLayoutManager mLayoutManager;
    private Spinner spCategory;
    private HomeDownloadManager homeDownloadManager;
    private View searchContainer;
    private FloatingActionButton openSearch;
    private View startSearch;
    private EditText etSearch;

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
        tvAdapter = new TvShowSearchResultAdapter();

        // Setup the category selector
        spCategory = (Spinner) findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.home_screen_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);
        spCategory.setOnItemSelectedListener(this);

        // Setup search
        searchContainer = findViewById(R.id.searchContainer);
        searchContainer.setAlpha(0f);
        searchContainer.setOnClickListener(null);
        searchContainer.setClickable(false);
        searchContainer.setFocusable(false);
        openSearch = (FloatingActionButton) findViewById(R.id.openSearch);
        openSearch.setOnClickListener(this);
        startSearch = findViewById(R.id.startSearch);
        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.setOnEditorActionListener(this);

        // Download data
        homeDownloadManager = new HomeDownloadManager(this);
        homeDownloadManager.downloadData(spCategory.getSelectedItemPosition(), null);

    }

    @Override
    public void onRefresh() {
        homeDownloadManager.downloadData(spCategory.getSelectedItemPosition(), null);
    }

    @Override
    public void showLoading(final boolean show){
        if( show ){
            movieAdapter.clearData();
            tvAdapter.clearData();
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
                        homeDownloadManager.downloadData(spCategory.getSelectedItemPosition(), null);
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
        searchResultContainer.setAdapter(tvAdapter);
        tvAdapter.showData(tvShowSearchResponse.getResults());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        homeDownloadManager.downloadData(position, null);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.openSearch){
            showSearchMode(true);
        }
        if( v.getId() == R.id.startSearch){
            homeDownloadManager.downloadData(spCategory.getSelectedItemPosition(), etSearch.getText().toString());
            showSearchMode(false);
        }
        if( v.getId() == R.id.searchContainer){
            showSearchMode(false);
        }
    }

    private void showSearchMode(boolean show){
        if (show) {
            searchContainer.animate()
                    .alpha(1f)
                    .setDuration(Const.SEARCH_REVEAL_ANIMATION_SPEED);
            openSearch.setAlpha(0f);
            searchContainer.setOnClickListener(this);
            searchContainer.setClickable(true);
            searchContainer.setFocusable(true);
            openSearch.setFocusable(false);
            openSearch.setClickable(false);
            startSearch.setOnClickListener(this);
        }
        else{
            searchContainer.animate()
                    .alpha(0f)
                    .setDuration(Const.SEARCH_REVEAL_ANIMATION_SPEED);
            openSearch.setAlpha(1f);
            searchContainer.setOnClickListener(null);
            searchContainer.setClickable(false);
            searchContainer.setFocusable(false);
            openSearch.setFocusable(true);
            openSearch.setClickable(true);
            startSearch.setOnClickListener(null);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            homeDownloadManager.downloadData(spCategory.getSelectedItemPosition(), etSearch.getText().toString());
            showSearchMode(false);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if( searchContainer.getAlpha() > 0 ){
            showSearchMode(false);
        }
        else {
            super.onBackPressed();
        }
    }
}
