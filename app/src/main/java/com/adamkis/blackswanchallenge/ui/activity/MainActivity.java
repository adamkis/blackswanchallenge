package com.adamkis.blackswanchallenge.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.common.Utils;
import com.adamkis.blackswanchallenge.model.Movie;
import com.adamkis.blackswanchallenge.network.GsonRequest;
import com.adamkis.blackswanchallenge.network.VolleySingleton;
import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadPopularMovies();
    }

    private void downloadPopularMovies(){

        GsonRequest popularMovieRequest = new GsonRequest(
                Const.buildPopularMovieRequestUrl(),
                Object.class,
                null,
                new Response.Listener<Object>() {
                    @Override
                    public void onResponse(Object object) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Utils.log(error.toString());
                    }
        });

        VolleySingleton.get(this).addToRequestQueue(popularMovieRequest);

    }

}
