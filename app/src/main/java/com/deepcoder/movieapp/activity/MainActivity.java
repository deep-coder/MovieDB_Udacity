package com.deepcoder.movieapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.deepcoder.movieapp.fragment.ConnectivityFragment;
import com.deepcoder.movieapp.fragment.FavouriteMovieFragment;
import com.deepcoder.movieapp.fragment.PopularMovieFragment;
import com.deepcoder.movieapp.fragment.R;
import com.deepcoder.movieapp.fragment.TopRatedMovieFragment;
import com.deepcoder.movieapp.utils.CheckConnectivity;

public class MainActivity extends BaseActivity {

    //public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    private boolean mTwoPane;
    String[] sortByItems = {"Popularity", "Rating", "Favourite"};
    private int sortType = 0;
    private static final String FRAGMENT_TYPE = "fragmenttype";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        if (savedInstanceState == null) {
            if (new CheckConnectivity(getApplicationContext()).isConnectedToInternet()) {
                toolbar.setTitle("Popular");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, PopularMovieFragment.newInstance()).commit();

            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ConnectivityFragment()).commit();
            }
            if (findViewById(R.id.movie_detail_container) != null) {
                mTwoPane = true;
            } else {
                mTwoPane = false;
            }
        }
        else
        {
            sortType=savedInstanceState.getInt(FRAGMENT_TYPE);
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(FRAGMENT_TYPE, sortType);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sort) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(sortByItems, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (sortType != which) {
                        sortType = which;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, getFragmentType(sortType))
                                .addToBackStack(null)
                                .commit();
                    }
                }
            }).create().show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Fragment getFragmentType(int sortType) {
        switch (sortType) {
            case 0:
                toolbar.setTitle("Popular");
                return PopularMovieFragment.newInstance();
            case 1:
                toolbar.setTitle("Top Rated");
                return TopRatedMovieFragment.newInstance();
            case 2:
                toolbar.setTitle("Favourite");
                return FavouriteMovieFragment.newInstance();

        }
        return null;
    }

}
