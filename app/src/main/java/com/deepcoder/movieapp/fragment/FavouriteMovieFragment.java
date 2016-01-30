package com.deepcoder.movieapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.deepcoder.movieapp.activity.MainActivity;
import com.deepcoder.movieapp.activity.MovieDetailsActivity;
import com.deepcoder.movieapp.adapter.movieAdapter;
import com.deepcoder.movieapp.data.MovieDBContract;
import com.deepcoder.movieapp.model.MovieDetails;
import com.deepcoder.movieapp.utils.Constants;
import com.deepcoder.movieapp.utils.JsonNetworkManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jdeepak on 1/20/2016.
 */
public class FavouriteMovieFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    String TAG = "MainActivity";
    private boolean isTablet;

    public static FavouriteMovieFragment newInstance() {
        FavouriteMovieFragment favouriteMovieFragment = new FavouriteMovieFragment();
        return favouriteMovieFragment;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Cursor cursor = getActivity().getContentResolver().query(MovieDBContract.MovieEntry.CONTENT_URI, null, null, null, null);
        getMovieListFromCursor(cursor);
        movieGridList.setAdapter(new movieAdapter(getActivity(), movieDetailsList));
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

    private void getMovieListFromCursor(Cursor cursor) {
        while (cursor.moveToNext()) {
            MovieDetails movie = new MovieDetails();
            movie.setMovieOriginalTitle(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_MOVIE_NAME)));
            movie.setMovieID(cursor.getInt(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_MOVIES_ID)));
            movie.setMovieRating(cursor.getDouble(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_RATING)));
            movie.setMovieDate(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_RELEASE_DATE)));
            movie.setImageThumbnail(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_IMAGE_URL)));
            movie.setOriginalLangauge(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_LANGUAGE)));
            movie.setAdultType(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_ADULT)));
            movie.setMoviePlot(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_OVERVIEW)));
            movie.setImageBackDrop(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_BACKDROP_PATH)));
            movie.setPopularity(cursor.getInt(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_POPULARITY)));
            movie.setVoteCount(cursor.getInt(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_VOTE_COUNT)));
            movie.setVideoAvailable(cursor.getString(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_VIDEO)));
            //movie.setVoteCount(cursor.getInt(cursor.getColumnIndex(MovieDBContract.MovieEntry.COLUMN_VOTE_AVERAGE)));
            movieDetailsList.add(movie);
        }
    }

}

