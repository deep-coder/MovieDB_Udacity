package com.deepcoder.movieapp.activity;

import android.os.Bundle;

import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.deepcoder.movieapp.fragment.PopularMovieFragment;
import com.deepcoder.movieapp.fragment.MovieDetailsFragment;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.fragment.R;

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
        movieDetails = (MovieDetails) getIntent().getParcelableExtra(MainActivity.PARCELABLE_KEY);
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(PopularMovieFragment.PARCELABLE_KEY, movieDetails);
            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(MainActivity.PARCELABLE_KEY,movieDetails);
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
