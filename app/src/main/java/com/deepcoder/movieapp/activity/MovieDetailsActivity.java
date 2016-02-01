package com.deepcoder.movieapp.activity;

import android.os.Bundle;

import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.deepcoder.movieapp.fragment.PopularMovieFragment;
import com.deepcoder.movieapp.fragment.MovieDetailsFragment;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.fragment.R;
import com.deepcoder.movieapp.utils.Constants;

import butterknife.ButterKnife;

/**
 * Created by jdeepak on 12/13/2015.
 */
public class MovieDetailsActivity extends BaseActivity {

    MovieDetails movieDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        movieDetails = (MovieDetails) getIntent().getParcelableExtra(Constants.PARCELABLE_KEY);
        if (savedInstanceState == null) {
            MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(movieDetails);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.PARCELABLE_KEY,movieDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
