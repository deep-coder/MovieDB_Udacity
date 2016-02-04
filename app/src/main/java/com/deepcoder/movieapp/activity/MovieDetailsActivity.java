package com.deepcoder.movieapp.activity;

import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.deepcoder.movieapp.fragment.MovieDetailsFragment;
import com.deepcoder.movieapp.fragment.R;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.utils.Constants;

import butterknife.ButterKnife;

/**
 * Created by jdeepak on 12/13/2015.
 */
public class MovieDetailsActivity extends BaseActivity{

    MovieDetails movieDetails;
    private ShareActionProvider mShareActionProvider;
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
        outState.putParcelable(Constants.PARCELABLE_KEY, movieDetails);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        MenuItem item=menu.findItem(R.id.action_share);
        mShareActionProvider=(ShareActionProvider)MenuItemCompat.getActionProvider(item);
        if (mShareActionProvider != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
            shareIntent.setType("text/plain");
            mShareActionProvider.setShareIntent(shareIntent);
        }
*/
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_share:
                Toast.makeText(this, "Hello",
                        Toast.LENGTH_LONG).show();
                break;
        }
*/
        return super.onOptionsItemSelected(item);
    }

}
