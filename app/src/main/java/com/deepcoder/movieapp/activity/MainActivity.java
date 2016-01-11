package com.deepcoder.movieapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.deepcoder.movieapp.fragment.ConnectivityFragment;
import com.deepcoder.movieapp.fragment.MainFragment;
import com.deepcoder.movieapp.fragment.MovieDetailsFragment;
import com.deepcoder.movieapp.fragment.R;
import com.deepcoder.movieapp.utils.CheckConnectivity;

public class MainActivity extends BaseActivity {

    public final static String PARCELABLE_KEY = "com.myapp.parcelable";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (new CheckConnectivity(getApplicationContext()).isConnectedToInternet()) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MainFragment(), "").commit();

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ConnectivityFragment(),"").commit();
        }
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            /*if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new MovieDetailsFragment())
                        .commit();
            }*/
        } else {
            mTwoPane = false;
        }
        boolean tabletSize = getResources().getBoolean(R.bool.isTablet);
        Log.i("Checl",tabletSize+"");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

        return super.onOptionsItemSelected(item);
    }

}
