package com.deepcoder.movieapp.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.deepcoder.movieapp.adapter.MovieListAdapter;
import com.deepcoder.movieapp.data.MovieDBContract;
import com.deepcoder.movieapp.model.MovieDetails;

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
        //super.onViewCreated(view, savedInstanceState);
        Cursor cursor = getActivity().getContentResolver().query(MovieDBContract.MovieEntry.CONTENT_URI, null, null, null, null);
        getMovieListFromCursor(cursor);
        movieGridList.setAdapter(new MovieListAdapter(getActivity(), movieDetailsList));
    }

    @Override
    protected void onScrollCompleted(int pageCount) {

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

