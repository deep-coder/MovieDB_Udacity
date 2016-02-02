package com.deepcoder.movieapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.deepcoder.movieapp.utils.Constants;

/**
 * Created by jdeepak on 1/13/2016.
 */
public class TopRatedMovieFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    String TAG = "MainActivity";
    private boolean isTablet;

    public static TopRatedMovieFragment newInstance() {
        TopRatedMovieFragment topRatedMovieFragment = new TopRatedMovieFragment();
        return topRatedMovieFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        jsonRequest(Constants.MOVIE_DB_SORT_VOTE_AVERAGE_URL,1+"");
    }

    @Override
    protected void onScrollCompleted(int pageCount) {
        jsonRequest(Constants.MOVIE_DB_SORT_VOTE_AVERAGE_URL,pageCount+"");
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

