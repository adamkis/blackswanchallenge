package com.adamkis.blackswanchallenge.ui.activity;

import android.os.Bundle;

import com.adamkis.blackswanchallenge.R;
import com.adamkis.blackswanchallenge.common.Const;
import com.adamkis.blackswanchallenge.model.Movie;
import com.adamkis.blackswanchallenge.ui.fragment.MovieDetailFragment;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if( extras.containsKey(Const.KEY_MOVIE_OBJECT) ){
            Movie movie = (Movie) extras.getSerializable(Const.KEY_MOVIE_OBJECT);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.rootContainer, MovieDetailFragment.newInstance(movie)).commit();
        }
        else if( extras.containsKey(Const.KEY_TV_OBJECT) ){

        }

    }
}
