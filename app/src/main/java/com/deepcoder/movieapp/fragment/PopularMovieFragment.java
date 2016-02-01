package com.deepcoder.movieapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepcoder.movieapp.utils.Constants;

/**
 * Created by jdeepak on 12/16/2015.
 */
public class PopularMovieFragment extends BaseFragment {


    String TAG = "MainActivity";


    public static PopularMovieFragment newInstance() {
        PopularMovieFragment popularMovieFragment = new PopularMovieFragment();
        return popularMovieFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonRequest(Constants.MOVIE_DB_SORT_POPULAR_URL,1+"");
    }

    @Override
    protected void onScrollCompleted(int pageCount) {
        jsonRequest(Constants.MOVIE_DB_SORT_POPULAR_URL,pageCount+"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
