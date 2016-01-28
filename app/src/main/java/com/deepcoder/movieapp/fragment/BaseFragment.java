package com.deepcoder.movieapp.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepcoder.movieapp.data.MovieDBContract;
import com.deepcoder.movieapp.model.MovieDetails;

/**
 * Created by jdeepak on 1/24/2016.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected boolean isFavourite(MovieDetails movieDetails){
        Uri uri = MovieDBContract.MovieEntry.CONTENT_URI.buildUpon().appendPath(movieDetails.getMovieID() + "").build();
        Cursor movieCursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        if (movieCursor.moveToNext() != true) {
            return false;
        } else {
            return true;
        }
    }

    protected void showSnackBarLong(CoordinatorLayout coordinatorLayout,String message){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    protected void showProgressBar(){

    }


}
