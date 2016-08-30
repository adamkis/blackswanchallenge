package com.adamkis.blackswanchallenge.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.common.Utils;
import com.adamkis.blackswanchallenge.model.MovieSearchResponse;
import com.adamkis.blackswanchallenge.network.GsonRequest;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.adamkis.blackswanchallenge.ui.adapter.MovieSearchResultAdapter;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private MovieSearchResultAdapter adapter;
    private RecyclerView searchResultContainer;
    private SwipeRefreshLayout swipeRefreshContainer;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchResultContainer = (RecyclerView) findViewById(R.id.searchResultContainer);
        swipeRefreshContainer = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshContainer);
        searchResultContainer.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        searchResultContainer.setLayoutManager(mLayoutManager);
        swipeRefreshContainer.setOnRefreshListener(this);

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
                        adapter = new MovieSearchResultAdapter(popularMovieResponse.getResults());
                        searchResultContainer.setAdapter(adapter);
                        showLoading(false);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Utils.log(error.toString());
                    }
                }
        );
        VolleySingleton.get(this).addToRequestQueue(popularMovieRequest);
    }

    @Override
    public void onRefresh() {
        downloadPopularMovies();
    }

    private void showLoading(boolean show){
        if( show ){
            if( adapter != null ){
                adapter.clearData();
            }
            swipeRefreshContainer.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshContainer.setRefreshing(true);
                }
            });
        }
        else{
            swipeRefreshContainer.setRefreshing(false);
        }
    }

}
