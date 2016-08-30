package com.adamkis.blackswanchallenge.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.common.Utils;
import com.adamkis.blackswanchallenge.model.Movie;
import com.adamkis.blackswanchallenge.model.MovieSearchResponse;
import com.adamkis.blackswanchallenge.network.GsonRequest;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.adamkis.blackswanchallenge.ui.adapter.MovieSearchResultAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private CoordinatorLayout clRoot;
    private MovieSearchResultAdapter adapter;
    private RecyclerView searchResultContainer;
    private SwipeRefreshLayout swipeRefreshContainer;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clRoot = (CoordinatorLayout) findViewById(R.id.clRoot);
        searchResultContainer = (RecyclerView) findViewById(R.id.searchResultContainer);
        swipeRefreshContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshContainer);
        searchResultContainer.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        searchResultContainer.setLayoutManager(mLayoutManager);
        swipeRefreshContainer.setOnRefreshListener(this);
        adapter = new MovieSearchResultAdapter();
        searchResultContainer.setAdapter(adapter);

        downloadPopularMovies();
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
                        adapter.showData(popularMovieResponse.getResults());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Utils.log(error.toString());
                        showLoading(false);
                        final Snackbar snackbar = Snackbar
                                .make(clRoot, getString(R.string.loading_failed), Snackbar.LENGTH_INDEFINITE)
                                .setAction(getString(R.string.retry), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadPopularMovies();
                                    }
                                });
                        snackbar.show();
                    }
                }
        );
        VolleySingleton.get(this).addToRequestQueue(popularMovieRequest);
    }

    @Override
    public void onRefresh() {
        downloadPopularMovies();
    }

    private void showLoading(final boolean show){
        if( show ){
            adapter.clearData();
        }
        swipeRefreshContainer.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshContainer.setRefreshing(show);
            }
        });
    }

}
